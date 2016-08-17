package vn.t3h.project1.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import java.util.List;
import vn.t3h.project1.R;
import vn.t3h.project1.dao.Dao;
import vn.t3h.project1.model.Order;
import vn.t3h.project1.model.Table;
import vn.t3h.project1.viewmodel.OrderAdapter;

public class OrderActivity extends Dao {
    private ListView lvOrder;
    private OrderAdapter adapter;
    private List<Order> orderList;
    private Button btFood;
    private Button btDrink;
    private int tableId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        lvOrder = (ListView) findViewById(R.id.lvOrder);
        btFood = (Button) findViewById(R.id.btFood);
        btDrink = (Button) findViewById(R.id.btDrink);
        tableId = getIntent().getExtras().getInt("tableId");
        btFood.setOnClickListener(v->startActivity(new Intent(OrderActivity.this, FoodActivity.class).putExtra("tableId", tableId)));
        btDrink.setOnClickListener(v->startActivity(new Intent(OrderActivity.this, DrinkActivity.class).putExtra("tableId", tableId)));
        loadDB();
        // Dang ky floating menu
        registerForContextMenu(lvOrder);
    }

    // Tao floating context menu
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.order_floating_menu, menu);
    }

    // Bat su kien floating menu
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int id = item.getItemId();
        AdapterView.AdapterContextMenuInfo contextMenuInfo =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        int pos = contextMenuInfo.position;
        Order currentOrder = orderList.get(pos);

        if(id == R.id.mnUpdate){
            Intent intent = new Intent(this, InputActivity.class);
            intent.putExtra("tableId", tableId);
            intent.putExtra("foodId", currentOrder.getFoodId());
            intent.putExtra("drinkId", currentOrder.getDrinkId());
            intent.putExtra("add", false); //Update ma khong phai la add
            startActivity(intent);
            adapter.notifyDataSetChanged();
        }else if(id == R.id.mnDel){
            // Xoa trong database
            delete(currentOrder);
            // Xoa trong list
            orderList.remove(pos);
            // Cap nhat lai giao dien
            adapter.notifyDataSetChanged();
        }

        // Updata table data
        updateTable();
        return super.onContextItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadDB();
    }

    private void loadDB(){
        orderList = getOrderData(tableId);
        adapter = new OrderAdapter(this, orderList);
        lvOrder.setAdapter(adapter);

        // Update table data
        updateTable();
    }

    private void updateTable(){
        Table table = new Table();
        table.setId(tableId);

        if (orderList.size()!=0){
            table.setStatus(1);
            int money = 0;
            for (Order tmp:orderList){
                money += tmp.getFoodQty()*tmp.getFoodPrice() + tmp.getDrinkQty()*tmp.getDrinkPrice();
            }
            table.setPrice(money);
        } else {
            table.setStatus(0);
            table.setPrice(0);
        }

        update(table);
    }
}
