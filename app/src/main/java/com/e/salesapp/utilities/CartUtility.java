package com.e.salesapp.utilities;

public class CartUtility {

    public static final int VERSION = 1;
    public static final String TABLE_CART = "cart";

    public static final String COLUMN_PRODUCT_ID = "id";
    public static final String COLUMN_PRODUCT_NAME = "name";
    public static final String COLUMN_PRODUCT_QUANTITY = "quantity";
    public static final String COLUMN_PRODUCT_PRICE = "price";
    public static final String COLUMN_PRODUCT_CATEGORY = "category";

    public static final String SQL_CREATE_CART   =
            "CREATE TABLE " + TABLE_CART + " (" +
                    COLUMN_PRODUCT_ID + " INTEGER PRIMARY KEY," +
                    COLUMN_PRODUCT_NAME + " TEXT," +
                    COLUMN_PRODUCT_QUANTITY + " INTEGER," +
                    COLUMN_PRODUCT_PRICE + " REAL," +
                    COLUMN_PRODUCT_CATEGORY + " TEXT)";

    public static final String SQL_DELETE_CLIENTS =
            "DROP TABLE IF EXISTS " + TABLE_CART;
}
