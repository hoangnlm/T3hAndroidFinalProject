package vn.t3h.project1;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;

import java.util.List;
import vn.t3h.project1.dao.Dao;
import vn.t3h.project1.model.MyTable;
import vn.t3h.project1.viewmodel.TableAdapter;

public class MainActivity extends Dao {
    private RecyclerView rvTableList;
    private TableAdapter adapter;
    private List<MyTable> myTableList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvTableList = (RecyclerView) findViewById(R.id.activity_main);
        rvTableList.setHasFixedSize(true);
        rvTableList.setLayoutManager(new LinearLayoutManager(this));

        myTableList = getTableData();
        adapter = new TableAdapter(myTableList);

        rvTableList.setAdapter(adapter);
    }
}
