package vn.t3h.project1.model;

import java.io.Serializable;

/**
 * Created by Hoang on 8/10/16.
 */

public class Table implements Serializable {
    private int id;
    private int status;
    private int price;

    public static String TABLE_NAME = "MyTable";
    public static String ID_COL_NAME = "id";
    public static String STATUS_COL_NAME = "status";
    public static String PRICE_COL_NAME = "price";

    public Table() {

    }

    public Table(int id, int status, int price) {
        this.id = id;
        this.status = status;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
