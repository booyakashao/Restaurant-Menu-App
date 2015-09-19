package com.wen.database.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.wen.database.DatabaseUtilities;

/**
 * Created by Wen on 9/19/2015.
 */
public class OrderItemsDAO extends DatabaseUtilities {

    public OrderItemsDAO(Context context) {
        super(context);
    }

    public boolean createNewOrderItem(Integer orderId, Integer menuId, Integer quantity) {
        SQLiteDatabase db = this.getWritableDatabase();

        /*
        This is not done yet!!!
         */
        ContentValues contentValues = new ContentValues();
        contentValues.put(ORDER_ITEM_COL_2, orderId);
        contentValues.put(ORDER_ITEM_COL_3, menuId);
        contentValues.put(ORDER_ITEM_COL_4, quantity);

        long result = db.insert(ORDER_ITEM_TABLE_NAME, null, contentValues);

        if(result == -1) {
            return false;
        } else {
            return true;
        }
    }
}
