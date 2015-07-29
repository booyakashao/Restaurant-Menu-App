package com.wen.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Wen on 7/27/2015.
 */
public class DatabaseUtilities extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "restaurant_menu_database.db";

    public static final String MENU_ITEM_TABLE_NAME = "menu_item";
    public static final String MENU_ITEM_COL_1 = "id";
    public static final String MENU_ITEM_COL_2 = "name";
    public static final String MENU_ITEM_COL_3 = "description";
    public static final String MENU_ITEM_COL_4 = "category_id";
    public static final String MENU_ITEM_COL_5 = "price";
    public static final String MENU_ITEM_COL_6 = "is_current_menu";

    public static final String CATEGORY_TABLE_NAME = "category";
    public static final String CATEGORY_COL_1 = "id";
    public static final String CATEGORY_COL_2 = "name";

    public static final String ORDER_ITEM_TABLE_NAME = "order_item";
    public static final String ORDER_ITEM_COL_1 = "id";
    public static final String ORDER_ITEM_COL_2 = "order_id";
    public static final String ORDER_ITEM_COL_3 = "menu_instance";
    public static final String ORDER_ITEM_COL_4 = "quantity";

    public static final String ORDERS_TABLE_NAME = "orders";
    public static final String ORDERS_COL_1 = "id";
    public static final String ORDERS_COL_2 = "create_time";

    public static final String TAX_RATE_TABLE_NAME = "tax_rate";
    public static final String TAX_RATE_COL_1 = "id";
    public static final String TAX_RATE_COL_2 = "tax_rate";

    public DatabaseUtilities(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " +
                MENU_ITEM_TABLE_NAME +
                "(" +
                MENU_ITEM_COL_1 +
                " INTEGER PRIMARY KEY AUTOINCREMENT," +
                MENU_ITEM_COL_2 +
                " TEXT," +
                MENU_ITEM_COL_3 +
                " TEXT," +
                MENU_ITEM_COL_4 +
                " INTEGER, " +
                MENU_ITEM_COL_5 +
                " REAL, " +
                MENU_ITEM_COL_6 +
                " INTEGER"+
                ")");

        db.execSQL("create table " +
                CATEGORY_TABLE_NAME +
                "(" +
                CATEGORY_COL_1 +
                " INTEGER PRIMARY KEY AUTOINCREMENT," +
                CATEGORY_COL_2 +
                " TEXT" +
                ")");

        db.execSQL("create table " +
                ORDER_ITEM_TABLE_NAME +
                "(" +
                ORDER_ITEM_COL_1 +
                " INTEGER PRIMARY KEY AUTOINCREMENT," +
                ORDER_ITEM_COL_2 +
                " INTEGER, " +
                ORDER_ITEM_COL_3 +
                " INTEGER, " +
                ORDER_ITEM_COL_4 +
                " INTEGER" +
                ")");

        db.execSQL("create table " +
                ORDERS_TABLE_NAME +
                "(" +
                ORDERS_COL_1 +
                " INTEGER PRIMARY KEY AUTOINCREMENT," +
                ORDERS_COL_2 +
                " REAL" +
                ")");

        db.execSQL("create table " +
                TAX_RATE_TABLE_NAME +
                "(" +
                TAX_RATE_COL_1 +
                " INTEGER PRIMARY KEY AUTOINCREMENT," +
                TAX_RATE_COL_2 +
                " REAL" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + MENU_ITEM_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + CATEGORY_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + ORDER_ITEM_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + ORDERS_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TAX_RATE_TABLE_NAME);

        onCreate(db);
    }
}
