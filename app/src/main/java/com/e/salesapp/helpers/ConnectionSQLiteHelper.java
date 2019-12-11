package com.e.salesapp.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.e.salesapp.models.Product;

import java.util.ArrayList;

import static com.e.salesapp.utilities.CartUtility.COLUMN_PRODUCT_CATEGORY;
import static com.e.salesapp.utilities.CartUtility.COLUMN_PRODUCT_ID;
import static com.e.salesapp.utilities.CartUtility.COLUMN_PRODUCT_NAME;
import static com.e.salesapp.utilities.CartUtility.COLUMN_PRODUCT_PRICE;
import static com.e.salesapp.utilities.CartUtility.COLUMN_PRODUCT_QUANTITY;
import static com.e.salesapp.utilities.CartUtility.SQL_CREATE_CART;
import static com.e.salesapp.utilities.CartUtility.TABLE_CART;

public class ConnectionSQLiteHelper extends SQLiteOpenHelper {

    public ConnectionSQLiteHelper(Context context, String tableName, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, tableName, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_CART);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_CART);
        onCreate(sqLiteDatabase);
    }

    public void dropTable(String table){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ table);
    }

    public boolean insertProduct(String name, int quantity, double price,String category) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cValues = new ContentValues();

        cValues.put(COLUMN_PRODUCT_NAME, name);
        cValues.put(COLUMN_PRODUCT_QUANTITY,quantity);
        cValues.put(COLUMN_PRODUCT_PRICE,price);
        cValues.put(COLUMN_PRODUCT_CATEGORY,category);

        long newRowId = db.insert(TABLE_CART, null, cValues);
        db.close();
        return (newRowId>0);
    }

    public ArrayList<Product> getProducts() {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<Product> productsList = new ArrayList<>();
        String query = "SELECT id, name, quantity, price,category FROM " + TABLE_CART;
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            Product product = new Product( cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_NAME)),
                    cursor.getInt(cursor.getColumnIndex(COLUMN_PRODUCT_QUANTITY)),
                    cursor.getDouble(cursor.getColumnIndex(COLUMN_PRODUCT_PRICE)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_CATEGORY)));
            product.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_PRODUCT_ID)));
            productsList.add(product);
        }
        cursor.close();
        return productsList;
    }

    public ArrayList<Product> getProductById(Product productToSearch) {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<Product> productsList = new ArrayList<>();
        Cursor cursor = db.query(TABLE_CART, new String[]{COLUMN_PRODUCT_NAME, COLUMN_PRODUCT_QUANTITY, COLUMN_PRODUCT_PRICE,COLUMN_PRODUCT_CATEGORY},
                COLUMN_PRODUCT_ID + "=?", new String[]{String.valueOf(productToSearch.getId())}, null, null, null, null);
        if (cursor.moveToNext()) {
            Product product = new Product( cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_NAME)),
                    cursor.getInt(cursor.getColumnIndex(COLUMN_PRODUCT_QUANTITY)),
                    cursor.getDouble(cursor.getColumnIndex(COLUMN_PRODUCT_PRICE)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_CATEGORY)));
            productsList.add(product);
        }
        cursor.close();
        return productsList;
    }

    public boolean deleteProduct(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();
        int flag = db.delete(TABLE_CART, COLUMN_PRODUCT_ID + " = ?", new String[]{String.valueOf(product.getId())});
        db.close();
        return (flag > 0);
    }

    public boolean updateProduct(String name, int quantity, double price,String category, Product product) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cValues = new ContentValues();

        cValues.put(COLUMN_PRODUCT_NAME, name);
        cValues.put(COLUMN_PRODUCT_QUANTITY,quantity);
        cValues.put(COLUMN_PRODUCT_PRICE,price);
        cValues.put(COLUMN_PRODUCT_CATEGORY,category);

        int flag = db.update(TABLE_CART, cValues, COLUMN_PRODUCT_ID + " = ?", new String[]{String.valueOf(product.getId())});
        return (flag > 0);
    }
}
