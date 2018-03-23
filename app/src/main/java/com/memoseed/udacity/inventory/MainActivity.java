package com.memoseed.udacity.inventory;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.memoseed.udacity.inventory.database.Contract;
import com.memoseed.udacity.inventory.database.DatabaseHandler;

import java.util.ArrayList;
import java.util.List;

import static com.memoseed.udacity.inventory.database.Contract.KEY_ID;
import static com.memoseed.udacity.inventory.database.Contract.KEY_PRICE;
import static com.memoseed.udacity.inventory.database.Contract.KEY_PRODUCT_NAME;
import static com.memoseed.udacity.inventory.database.Contract.KEY_QUANTITY;
import static com.memoseed.udacity.inventory.database.Contract.KEY_SUPPLIER_NAME;
import static com.memoseed.udacity.inventory.database.Contract.KEY_SUPPLIER_PHONE;
import static com.memoseed.udacity.inventory.database.Contract.TABLE_STORE;

public class MainActivity extends AppCompatActivity {

    DatabaseHandler databaseHandler;
    List<Contract.Product> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHandler = new DatabaseHandler(this);
        list = getAllProducts();
    }


    // Getting All Products
    private List<Contract.Product> getAllProducts() {
        List<Contract.Product> productList = new ArrayList<Contract.Product>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_STORE;
        SQLiteDatabase db = databaseHandler.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        Contract contract = new Contract();

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Contract.Product product = contract.new Product(
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
