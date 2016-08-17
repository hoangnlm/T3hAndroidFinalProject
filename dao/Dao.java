package vn.t3h.project1.dao;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
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

import vn.t3h.project1.model.Drink;
import vn.t3h.project1.model.Food;
import vn.t3h.project1.model.Table;
import vn.t3h.project1.model.Order;

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
     * Truy xuat thong tin Table tu database
     */
    protected List<Table> getTableData() {
        // Mo ket noi toi db
        db = openDB();
        // Truy van du lieu tu table
        Cursor cursor = db.query(Table.TABLE_NAME, null, null, null, null, null, Table.ID_COL_NAME + " ASC");
        List<Table> tables = new ArrayList<>();
        if (cursor != null && cursor.moveToFirst()) {
            do {
                Table table = new Table();
                table.setId(cursor.getInt(cursor.getColumnIndex(Table.ID_COL_NAME)));
                table.setStatus(cursor.getInt(cursor.getColumnIndex(Table.STATUS_COL_NAME)));
                table.setPrice(cursor.getInt(cursor.getColumnIndex(Table.PRICE_COL_NAME)));
                tables.add(table);
            } while (cursor.moveToNext());
            cursor.close();
        }
        db.close();
        return tables;
    }

    /**
     * Truy xuat thong tin Food tu database
     */
    protected List<Food> getFoodData() {
        // Mo ket noi toi db
        db = openDB();
        // Truy van du lieu tu table
        Cursor cursor = db.query(Food.TABLE_NAME, null, null, null, null, null, Food.ID_COL_NAME + " ASC");
        List<Food> foods = new ArrayList<>();
        if (cursor != null && cursor.moveToFirst()) {
            do {
                Food food = new Food();
                food.setId(cursor.getInt(cursor.getColumnIndex(Food.ID_COL_NAME)));
                food.setName(cursor.getString(cursor.getColumnIndex(Food.NAME_COL_NAME)));
                food.setPrice(cursor.getInt(cursor.getColumnIndex(Food.PRICE_COL_NAME)));
                food.setImage(cursor.getString(cursor.getColumnIndex(Food.IMAGE_COL_NAME)));
                food.setRate(cursor.getFloat(cursor.getColumnIndex(Food.RATE_COL_NAME)));
                foods.add(food);
            } while (cursor.moveToNext());
            cursor.close();
        }
        db.close();
        return foods;
    }

    /**
     * Truy xuat thong tin Drink tu database
     */
    protected List<Drink> getDrinkData() {
        // Mo ket noi toi db
        db = openDB();
        // Truy van du lieu tu table
        Cursor cursor = db.query(Drink.TABLE_NAME, null, null, null, null, null, Food.ID_COL_NAME + " ASC");
        List<Drink> drinks = new ArrayList<>();
        if (cursor != null && cursor.moveToFirst()) {
            do {
                Drink drink = new Drink();
                drink.setId(cursor.getInt(cursor.getColumnIndex(Drink.ID_COL_NAME)));
                drink.setName(cursor.getString(cursor.getColumnIndex(Drink.NAME_COL_NAME)));
                drink.setPrice(cursor.getInt(cursor.getColumnIndex(Drink.PRICE_COL_NAME)));
                drink.setImage(cursor.getString(cursor.getColumnIndex(Drink.IMAGE_COL_NAME)));
                drink.setRate(cursor.getFloat(cursor.getColumnIndex(Drink.RATE_COL_NAME)));
                drinks.add(drink);
            } while (cursor.moveToNext());
            cursor.close();
        }
        db.close();
        return drinks;
    }

    /**
     * Truy xuat thong tin Order tu database
     */
    protected List<Order> getOrderData(int tableId) {
        // Mo ket noi toi db
        db = openDB();
        // Truy van du lieu tu table
        String sql = "select o.myTableId, o.foodId, o.foodQty, f.name as foodName, f.price as foodPrice, o.drinkId, o.drinkQty, d.name as drinkName, d.price as drinkPrice from Order2 as o left join Food as f on o.foodId=f.id left join Drink as d on o.drinkId=d.id where o.myTableId=" + tableId + " order by o.myTableId";
        Cursor cursor = db.rawQuery(sql, null);
        List<Order> orders = new ArrayList<>();
        if (cursor != null && cursor.moveToFirst()) {
            do {
                Order order = new Order();
                order.setTableId(cursor.getInt(cursor.getColumnIndex(Order.TABLEID_COL_NAME)));
                order.setFoodId(cursor.getInt(cursor.getColumnIndex(Order.FOODID_COL_NAME)));
                order.setFoodName(cursor.getString(cursor.getColumnIndex(Order.FOODNAME_COL_NAME)));
                order.setFoodQty(cursor.getInt(cursor.getColumnIndex(Order.FOODQTY_COL_NAME)));
                order.setFoodPrice(cursor.getInt(cursor.getColumnIndex(Order.FOODPRICE_COL_NAME)));
                order.setDrinkId(cursor.getInt(cursor.getColumnIndex(Order.DRINKID_COL_NAME)));
                order.setDrinkName(cursor.getString(cursor.getColumnIndex(Order.DRINKNAME_COL_NAME)));
                order.setDrinkQty(cursor.getInt(cursor.getColumnIndex(Order.DRINKQTY_COL_NAME)));
                order.setDrinkPrice(cursor.getInt(cursor.getColumnIndex(Order.DRINKPRICE_COL_NAME)));
                orders.add(order);
            } while (cursor.moveToNext());
            cursor.close();
        }
        // Dong ket noi db
        db.close();
        return orders;
    }

    /**
     * Truy xuat thong tin Order tu database
     */
    protected List<Order> getOrderData(int tableId, int foodId, int drinkId) {
        // Mo ket noi toi db
        db = openDB();
        // Truy van du lieu tu table
        String sql = "";
        if (foodId!=0) {
            sql = "select o.myTableId, o.foodId, o.foodQty, f.name as foodName, f.price as foodPrice, o.drinkId, o.drinkQty, d.name as drinkName, d.price as drinkPrice from Order2 as o left join Food as f on o.foodId=f.id left join Drink as d on o.drinkId=d.id where o.myTableId=" + tableId + " AND o.foodId=" + foodId + " order by o.myTableId";
        } else {
            sql = "select o.myTableId, o.foodId, o.foodQty, f.name as foodName, f.price as foodPrice, o.drinkId, o.drinkQty, d.name as drinkName, d.price as drinkPrice from Order2 as o left join Food as f on o.foodId=f.id left join Drink as d on o.drinkId=d.id where o.myTableId=" + tableId + " AND o.drinkId=" + drinkId + " order by o.myTableId";
        }
        Cursor cursor = db.rawQuery(sql, null);
        List<Order> orders = new ArrayList<>();
        if (cursor != null && cursor.moveToFirst()) {
            do {
                Order order = new Order();
                order.setTableId(cursor.getInt(cursor.getColumnIndex(Order.TABLEID_COL_NAME)));
                order.setFoodId(cursor.getInt(cursor.getColumnIndex(Order.FOODID_COL_NAME)));
                order.setFoodName(cursor.getString(cursor.getColumnIndex(Order.FOODNAME_COL_NAME)));
                order.setFoodQty(cursor.getInt(cursor.getColumnIndex(Order.FOODQTY_COL_NAME)));
                order.setFoodPrice(cursor.getInt(cursor.getColumnIndex(Order.FOODPRICE_COL_NAME)));
                order.setDrinkId(cursor.getInt(cursor.getColumnIndex(Order.DRINKID_COL_NAME)));
                order.setDrinkName(cursor.getString(cursor.getColumnIndex(Order.DRINKNAME_COL_NAME)));
                order.setDrinkQty(cursor.getInt(cursor.getColumnIndex(Order.DRINKQTY_COL_NAME)));
                order.setDrinkPrice(cursor.getInt(cursor.getColumnIndex(Order.DRINKPRICE_COL_NAME)));
                orders.add(order);
            } while (cursor.moveToNext());
            cursor.close();
        }
        // Dong ket noi db
        db.close();
        return orders;
    }

    /**
     * Insert new order
     *
     * @param order
     * @return
     */
    public long insert(Order order) {
        // Mo ket noi toi db
        db = openDB();
        // Insert db
        long record = db.insert(Order.TABLE_NAME, null, mapOrderContentValues(order));
        // Dong ket noi db
        db.close();
        return record;
    }

    /**
     * Update order
     * @param order
     * @return
     */
    public int update(Order order) {
        // Mo ket noi toi db
        db = openDB();

        // Update db
        StringBuilder where = new StringBuilder(Order.TABLEID_COL_NAME + "=" + order.getTableId());
        where.append(order.getFoodId() != 0 ? (" AND " + Order.FOODID_COL_NAME + "=" + order.getFoodId()) : (" AND " + Order.DRINKID_COL_NAME + "=" + order.getDrinkId()));
        int count = db.update(Order.TABLE_NAME, mapOrderContentValues(order), where.toString(), null);

        // Dong ket noi db
        db.close();
        return count;
    }

    /**
     * Update table info
     * @param table
     * @return
     */
    public int update(Table table) {
        // Mo ket noi toi db
        db = openDB();

        // Update db
        int count = db.update(Table.TABLE_NAME, mapTableContentValues(table), Table.ID_COL_NAME+"="+table.getId(), null);

        // Dong ket noi db
        db.close();
        return count;
    }

    public int delete(Order order){
        // Mo ket noi toi db
        db = openDB();

        // Delete db
        StringBuilder where = new StringBuilder(Order.TABLEID_COL_NAME + "=" + order.getTableId());
        where.append(order.getFoodId() != 0 ? (" AND " + Order.FOODID_COL_NAME + "=" + order.getFoodId()) : (" AND " + Order.DRINKID_COL_NAME + "=" + order.getDrinkId()));
        int count = db.delete(Order.TABLE_NAME, where.toString(), null);

        // Dong ket noi db
        db.close();
        return count;
    }

    protected ContentValues mapTableContentValues(Table table) {
        ContentValues cv = new ContentValues();
        cv.put(Table.ID_COL_NAME, table.getId());
        cv.put(Table.STATUS_COL_NAME, table.getStatus());
        cv.put(Table.PRICE_COL_NAME, table.getPrice());
        return cv;
    }

    protected ContentValues mapFoodContentValues(Food food) {
        ContentValues cv = new ContentValues();
        cv.put(Food.ID_COL_NAME, food.getId());
        cv.put(Food.NAME_COL_NAME, food.getName());
        cv.put(Food.PRICE_COL_NAME, food.getPrice());
        cv.put(Food.IMAGE_COL_NAME, food.getImage());
        cv.put(Food.RATE_COL_NAME, food.getRate());
        return cv;
    }

    protected ContentValues mapDrinkContentValues(Drink drink) {
        ContentValues cv = new ContentValues();
        cv.put(Drink.ID_COL_NAME, drink.getId());
        cv.put(Drink.NAME_COL_NAME, drink.getName());
        cv.put(Drink.PRICE_COL_NAME, drink.getPrice());
        cv.put(Drink.IMAGE_COL_NAME, drink.getImage());
        cv.put(Drink.RATE_COL_NAME, drink.getRate());
        return cv;
    }

    protected ContentValues mapOrderContentValues(Order order) {
        ContentValues cv = new ContentValues();
        cv.put(Order.TABLEID_COL_NAME, order.getTableId());
        cv.put(Order.FOODID_COL_NAME, order.getFoodId());
        cv.put(Order.FOODQTY_COL_NAME, order.getFoodQty());
        cv.put(Order.DRINKID_COL_NAME, order.getDrinkId());
        cv.put(Order.DRINKQTY_COL_NAME, order.getDrinkQty());
        return cv;
    }

    /**
     * Mo ket noi toi database
     */
    protected SQLiteDatabase openDB() {
        return SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READWRITE);
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
            Log.d(TAG, "Duong dan: " + path);
        }
    }
}
