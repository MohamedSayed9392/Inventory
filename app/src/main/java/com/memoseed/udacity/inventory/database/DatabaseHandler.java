package com.memoseed.udacity.inventory.database;
 
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import static com.memoseed.udacity.inventory.database.Contract.KEY_ID;
import static com.memoseed.udacity.inventory.database.Contract.KEY_PRICE;
import static com.memoseed.udacity.inventory.database.Contract.KEY_PRODUCT_NAME;
import static com.memoseed.udacity.inventory.database.Contract.KEY_QUANTITY;
import static com.memoseed.udacity.inventory.database.Contract.KEY_SUPPLIER_NAME;
import static com.memoseed.udacity.inventory.database.Contract.KEY_SUPPLIER_PHONE;
import static com.memoseed.udacity.inventory.database.Contract.TABLE_STORE;

import com.memoseed.udacity.inventory.database.Contract.Product;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "RetailStoreDB";
 
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
 
    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_STORE_TABLE = "CREATE TABLE " + TABLE_STORE + "("
                + KEY_ID + " int PRIMARY KEY,"
                + KEY_PRODUCT_NAME + " TEXT,"
                + KEY_PRICE + " DOUBLE,"
                + KEY_QUANTITY + " INT,"
                + KEY_SUPPLIER_NAME + " TEXT,"
                + KEY_SUPPLIER_PHONE + " TEXT"
                + ")";

        db.execSQL(CREATE_STORE_TABLE);
    }
 
    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STORE);
        // Create tables again
        onCreate(db);
    }


    // Adding new product
    public void addProduct(Contract.Product product) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put(KEY_ID, product.getId());
        values.put(KEY_PRICE, product.getPrice());
        values.put(KEY_PRODUCT_NAME, product.getProductName());
        values.put(KEY_QUANTITY, product.getQuantity());
        values.put(KEY_SUPPLIER_NAME, product.getSupplierName());
        values.put(KEY_SUPPLIER_PHONE, product.getSupplierPhone());
        // Inserting Row
        db.insert(TABLE_STORE, null, values);
        db.close(); // Closing database connection
    }

    // Getting All Products
    public List<Product> getAllProducts() {
        List<Product> productList = new ArrayList<Product>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_STORE;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        Contract contract = new Contract();

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Product product = contract.new Product(
                        cursor.getInt(cursor.getColumnIndex(KEY_ID)),
                        cursor.getInt(cursor.getColumnIndex(KEY_QUANTITY)),
                        cursor.getDouble(cursor.getColumnIndex(KEY_PRICE)),
                        cursor.getString(cursor.getColumnIndex(KEY_PRODUCT_NAME)),
                        cursor.getString(cursor.getColumnIndex(KEY_SUPPLIER_NAME)),
                        cursor.getString(cursor.getColumnIndex(KEY_SUPPLIER_PHONE))
                );
                // Adding product to list
                productList.add(product);
            } while (cursor.moveToNext());
        }

        // return product list
        return productList;
    }
 
}