package vn.t3h.project1.model;

/**
 * Created by Hoang on 8/10/16.
 */

public class Drink {
    private int id;
    private String name;
    private int price;
    private String image;
    private float rate;

    public static String TABLE_NAME = "Drink";
    public static String ID_COL_NAME = "id";
    public static String NAME_COL_NAME = "name";
    public static String PRICE_COL_NAME = "price";
    public static String IMAGE_COL_NAME = "image";
    public static String RATE_COL_NAME = "rate";

    public Drink() {
    }

    public Drink(int id, String name, int price, String image, float rate) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;
        this.rate = rate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }
}
