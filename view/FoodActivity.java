package vn.t3h.project1.view;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import java.util.List;
import vn.t3h.project1.R;
import vn.t3h.project1.dao.Dao;
import vn.t3h.project1.model.Food;
import vn.t3h.project1.viewmodel.FoodAdapter;

public class FoodActivity extends Dao {
    private RecyclerView rvFoodList;
    private FoodAdapter adapter;
    private List<Food> foodList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        rvFoodList = (RecyclerView) findViewById(R.id.activity_food);
        rvFoodList.setHasFixedSize(true);
        rvFoodList.setLayoutManager(new LinearLayoutManager(this));
        loadDB();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadDB();
    }

    private void loadDB(){
        foodList = getFoodData();
        adapter = new FoodAdapter(this, foodList, getIntent().getIntExtra("tableId", 0));
        rvFoodList.setAdapter(adapter);
    }
}
