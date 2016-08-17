package vn.t3h.project1.view;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import java.util.List;
import vn.t3h.project1.R;
import vn.t3h.project1.dao.Dao;
import vn.t3h.project1.model.Drink;
import vn.t3h.project1.viewmodel.DrinkAdapter;

public class DrinkActivity extends Dao {
    private RecyclerView rvDrinkList;
    private DrinkAdapter adapter;
    private List<Drink> drinkList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);
        rvDrinkList = (RecyclerView) findViewById(R.id.activity_drink);
        rvDrinkList.setHasFixedSize(true);
        rvDrinkList.setLayoutManager(new LinearLayoutManager(this));
        loadDB();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadDB();
    }

    private void loadDB(){
        drinkList = getDrinkData();
        adapter = new DrinkAdapter(this, drinkList, getIntent().getIntExtra("tableId", 0));
        rvDrinkList.setAdapter(adapter);
    }
}
