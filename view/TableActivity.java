package vn.t3h.project1.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.io.Serializable;
import java.util.List;

import vn.t3h.project1.R;
import vn.t3h.project1.dao.Dao;
import vn.t3h.project1.model.Table;
import vn.t3h.project1.viewmodel.OrderAdapter;
import vn.t3h.project1.viewmodel.TableAdapter;

public class TableActivity extends Dao {
    private RecyclerView rvTableList;
    private TableAdapter adapter;
    private List<Table> tableList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);
        rvTableList = (RecyclerView) findViewById(R.id.activity_main);
        rvTableList.setHasFixedSize(true);
        rvTableList.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadDB();
    }

    private void loadDB(){
        tableList = getTableData();
        adapter = new TableAdapter(this, tableList);
        rvTableList.setAdapter(adapter);
    }
}
