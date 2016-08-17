package vn.t3h.project1.view;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

import vn.t3h.project1.R;
import vn.t3h.project1.dao.Dao;
import vn.t3h.project1.model.Order;

public class InputActivity extends Dao {
    private EditText etQty;
    private Button btOk;
    private boolean add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        etQty = (EditText) findViewById(R.id.etQuantity);
        btOk = (Button) findViewById(R.id.btOk);
        btOk.setOnClickListener(view -> saveDB());
        add = getIntent().getBooleanExtra("add", true);
    }

    private void saveDB() {
        if (!etQty.getText().toString().trim().isEmpty()){
            int qty = Integer.parseInt(etQty.getText().toString());
            if (0<qty && qty<=50){
                // Cap nhat table Order trong database
                Order order = new Order();
                order.setTableId(getIntent().getIntExtra("tableId", 0));
                order.setFoodId(getIntent().getIntExtra("foodId", 0));
                order.setDrinkId(getIntent().getIntExtra("drinkId", 0));

                if (order.getFoodId()!=0){
                    if (add) {
                        // Kiem tra mon an da co trong order chua, neu roi thi cong them so luong
                        List<Order> tmpList = getOrderData(order.getTableId(), order.getFoodId(), 0);
                        if (tmpList.size() > 0) {
                            Order tmp = tmpList.get(0);
                            qty += tmp.getFoodQty();
                        }
                    }
                    order.setFoodQty(qty);

                } else {
                    if (add) {
                        // Kiem tra mon uong da co trong order chua, neu roi thi cong them so luong
                        List<Order> tmpList = getOrderData(order.getTableId(), 0, order.getDrinkId());
                        if (tmpList.size() > 0) {
                            Order tmp = tmpList.get(0);
                            qty += tmp.getDrinkQty();
                        }
                    }
                    order.setDrinkQty(qty);
                }

                if (update(order)==0)
                    insert(order);

                finish();
            } else {
                Snackbar.make(btOk, "Chỉ chọn 0-50!" + qty, Snackbar.LENGTH_SHORT).show();
            }
        }
    }
}
