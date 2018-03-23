package com.memoseed.udacity.inventory.database;

/**
 * Created by Mohamed Sayed on 3/23/2018.
 */

public class Contract {

    public static final String TABLE_STORE = "store";

    public static final String KEY_ID = "id";
    public static final String KEY_PRODUCT_NAME = "product_name";
    public static final String KEY_PRICE = "price";
    public static final String KEY_QUANTITY = "quantity";
    public static final String KEY_SUPPLIER_NAME = "supplier_name";
    public static final String KEY_SUPPLIER_PHONE = "supplier_phone";

    public class Product {
        int id,quantity;
        double price;
        String productName,supplierName,supplierPhone;

        public Product(int id, int quantity, double price, String productName, String supplierName, String supplierPhone) {
            this.id = id;
            this.quantity = quantity;
            this.price = price;
            this.productName = productName;
            this.supplierName = supplierName;
            this.supplierPhone = supplierPhone;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public String getSupplierName() {
            return supplierName;
        }

        public void setSupplierName(String supplierName) {
            this.supplierName = supplierName;
        }

        public String getSupplierPhone() {
            return supplierPhone;
        }

        public void setSupplierPhone(String supplierPhone) {
            this.supplierPhone = supplierPhone;
        }
    }


}
