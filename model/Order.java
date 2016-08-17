package vn.t3h.project1.model;

import java.util.List;

/**
 * Created by Hoang on 8/10/16.
 */

public class Order {
    private int tableId;
    private int foodId;
    private String foodName;
    private int foodQty;
    private int foodPrice;
    private int drinkId;
    private String drinkName;
    private int drinkQty;
    private int drinkPrice;

    public static String TABLE_NAME = "Order2";
    public static String TABLEID_COL_NAME = "myTableId";
    public static String FOODID_COL_NAME = "foodId";
    public static String FOODNAME_COL_NAME = "foodName";
    public static String FOODQTY_COL_NAME = "foodQty";
    public static String FOODPRICE_COL_NAME = "foodPrice";
    public static String DRINKID_COL_NAME = "drinkId";
    public static String DRINKNAME_COL_NAME = "drinkName";
    public static String DRINKQTY_COL_NAME = "drinkQty";
    public static String DRINKPRICE_COL_NAME = "drinkPrice";

    public Order() {
    }

    public Order(int tableId, int foodId, String foodName, int foodQty, int foodPrice, int drinkId, String drinkName, int drinkQty, int drinkPrice) {
        this.tableId = tableId;
        this.foodId = foodId;
        this.foodName = foodName;
        this.foodQty = foodQty;
        this.foodPrice = foodPrice;
        this.drinkId = drinkId;
        this.drinkName = drinkName;
        this.drinkQty = drinkQty;
        this.drinkPrice = drinkPrice;
    }

    public int getFoodSum(){
        return foodPrice*foodQty;
    }

    public int getDrinkSum(){
        return drinkPrice*drinkQty;
    }

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public int getFoodQty() {
        return foodQty;
    }

    public void setFoodQty(int foodQty) {
        this.foodQty = foodQty;
    }

    public int getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(int foodPrice) {
        this.foodPrice = foodPrice;
    }

    public int getDrinkId() {
        return drinkId;
    }

    public void setDrinkId(int drinkId) {
        this.drinkId = drinkId;
    }

    public String getDrinkName() {
        return drinkName;
    }

    public void setDrinkName(String drinkName) {
        this.drinkName = drinkName;
    }

    public int getDrinkQty() {
        return drinkQty;
    }

    public void setDrinkQty(int drinkQty) {
        this.drinkQty = drinkQty;
    }

    public int getDrinkPrice() {
        return drinkPrice;
    }

    public void setDrinkPrice(int drinkPrice) {
        this.drinkPrice = drinkPrice;
    }
}
