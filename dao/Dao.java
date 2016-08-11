package vn.t3h.project1.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import vn.t3h.project1.model.MyTable;

/**
 * Created by Hoang on 8/11/16.
 */

public class Dao extends AppCompatActivity {

    protected SQLiteDatabase db;
    protected String path;
    public static String DB_FILENAME = "foodDB.db";
    public static final String TAG = "Dao";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDB();
    }

    /**
     * Kiem tra file DB da co trong internal storage chua, neu chua thi copy tu bundle vao.
     */
    protected void initDB() {
        path = getFilesDir().getAbsolutePath() + "/" + DB_FILENAME;
        File file = new File(path);
        if (!file.exists()) {
            try {
                BufferedInputStream in = new BufferedInputStream(getAssets().open(DB_FILENAME));
                FileOutputStream out = openFileOutput(DB_FILENAME, Context.MODE_PRIVATE);
                byte[] buffer = new byte[4096];
                int i = 0;
                while ((i = in.read(buffer)) != -1) {
                    out.write(buffer, 0, i);
                }
                in.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Log.d(TAG, "Duong dan: "+path);
        }
    }

    /**
     * Mo ket noi toi database
     */

    protected SQLiteDatabase openDB(){
        return SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READWRITE);
    }

    /**
     * Truy xuat thong tin MyTable tu database
     */
    protected List<MyTable> getTableData(){
        // Mo ket noi toi db
        db = openDB();
        // Truy van du lieu tu table
        Cursor cursor = db.query(MyTable.TABLE_NAME, null, null, null, null, null, MyTable.ID_COL_NAME + " ASC");
        List<MyTable> myTables = new ArrayList<>();
        if (cursor!=null && cursor.moveToFirst()){
            do {
                MyTable myTable = new MyTable();
                myTable.setId(cursor.getInt(cursor.getColumnIndex(MyTable.ID_COL_NAME)));
                myTable.setStatus(cursor.getInt(cursor.getColumnIndex(MyTable.STATUS_COL_NAME)));
                myTable.setPrice(cursor.getInt(cursor.getColumnIndex(MyTable.PRICE_COL_NAME)));
                myTables.add(myTable);
            } while (cursor.moveToNext());
            cursor.close();
        }
        db.close();
        return myTables;
    }

    protected ContentValues getTableContentValues(MyTable myTable){
        ContentValues cv = new ContentValues();
        cv.put(MyTable.ID_COL_NAME, myTable.getId());
        cv.put(MyTable.STATUS_COL_NAME, myTable.getStatus());
        cv.put(MyTable.PRICE_COL_NAME, myTable.getPrice());
        return cv;
    }
}
