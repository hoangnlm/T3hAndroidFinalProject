package vn.t3h.project1.model;

import java.util.List;

/**
 * Created by Hoang on 8/10/16.
 */

public class Order {
    private int tableId;
    private List<Integer> foodId;
    private List<Integer> drinkId;

    public static String TABLE_NAME = "Order";
    public static String TABLEID_COL_NAME = "tableId";
    public static String FOODID_COL_NAME = "foodId";
    public static String DRINKID_COL_NAME = "drinkId";

    public Order() {
    }

    public Order(int tableId, List<Integer> foodId, List<Integer> drinkId) {
        this.tableId = tableId;
        this.foodId = foodId;
        this.drinkId = drinkId;
    }
    
    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public List<Integer> getFoodId() {
        return foodId;
    }

    public void setFoodId(List<Integer> foodId) {
        this.foodId = foodId;
    }

    public List<Integer> getDrinkId() {
        return drinkId;
    }

    public void setDrinkId(List<Integer> drinkId) {
        this.drinkId = drinkId;
    }
}
