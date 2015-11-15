package com.wen.database.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.wen.database.DatabaseUtilities;
import com.wen.database.model.Orders;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Wen on 9/18/2015.
 */
public class OrdersDAO extends DatabaseUtilities {
    public OrdersDAO(Context context) {
        super(context);
    }

    public boolean hasCurrentOrder() {
        SQLiteDatabase db = this.getReadableDatabase();

        List<String> tableColumns = new ArrayList<String>();
        tableColumns.add("count(*)");

        String whereClause = ORDERS_COL_4 + " = ?";

        List<String> whereArgs = new ArrayList<String>();
        whereArgs.add("1");

        Cursor ordersCursor = db.query(ORDERS_TABLE_NAME, tableColumns.toArray(new String[tableColumns.size()]), whereClause, whereArgs.toArray(new String[whereArgs.size()]), null, null, null);

        ordersCursor.moveToFirst();
        int cursorCount = ordersCursor.getInt(0);

        db.close();

        if(cursorCount > 0) {
            return true;
        } else {
            return false;
        }
    }

    public Orders getCurrentOrder() {
        SQLiteDatabase db = this.getReadableDatabase();

        Orders orderToBeReturn = new Orders();

        List<String> tableColumns = new ArrayList<String>();
        tableColumns.add("*");

        String whereClause = ORDERS_COL_4 + " = ?";

        List<String> whereArgs = new ArrayList<String>();
        whereArgs.add("1");

        Cursor ordersCursor = db.query(ORDERS_TABLE_NAME, tableColumns.toArray(new String[tableColumns.size()]), whereClause, whereArgs.toArray(new String[whereArgs.size()]), null, null, null);

        ordersCursor.moveToFirst();

        orderToBeReturn.setId(ordersCursor.getInt(0));
        orderToBeReturn.setCreateTime(new Date(ordersCursor.getLong(1)));

        if(ordersCursor.getInt(2) == 1) {
            orderToBeReturn.setFulfilled(true);
        } else {
            orderToBeReturn.setFulfilled(false);
        }

        if(ordersCursor.getInt(3) == 1) {
            orderToBeReturn.setCurrent(true);
        } else {
            orderToBeReturn.setCurrent(false);
        }

        db.close();

        return orderToBeReturn;
    }

    public boolean createNewOrder() {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(ORDERS_COL_2, new Date().getTime());
        contentValues.put(ORDERS_COL_3, 0);
        contentValues.put(ORDERS_COL_4, 1);

        long result = db.insert(ORDERS_TABLE_NAME, null, contentValues);

        db.close();

        if(result == -1) {
            return false;
        } else {
            return true;
        }
    }
}
