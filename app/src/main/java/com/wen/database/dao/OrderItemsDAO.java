package com.wen.database.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.wen.database.DatabaseUtilities;
import com.wen.database.model.Menu_Item;
import com.wen.database.model.OrderItems;
import com.wen.database.model.Orders;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wen on 9/19/2015.
 */
public class OrderItemsDAO extends DatabaseUtilities {

    public OrderItemsDAO(Context context) {
        super(context);
    }

    public boolean createNewOrderItem(Integer orderId, Integer menuId, Integer quantity) {
        SQLiteDatabase db = this.getWritableDatabase();

        long result = 0;

        OrderItems existingOrder = getExistingOrderItem(orderId, menuId);

        if(existingOrder != null) {
            Integer newQuantity = quantity + existingOrder.getQuantity();

            ContentValues contentValues = new ContentValues();
            contentValues.put(ORDER_ITEM_COL_4, newQuantity);

            String whereClause = ORDER_ITEM_COL_1 + " = ? ";

            List<String> whereArgs = new ArrayList<String>();
            whereArgs.add(existingOrder.getId().toString());

            result = db.update(ORDER_ITEM_TABLE_NAME, contentValues, whereClause, whereArgs.toArray(new String[whereArgs.size()]));
        } else {
            ContentValues contentValues = new ContentValues();
            contentValues.put(ORDER_ITEM_COL_2, orderId);
            contentValues.put(ORDER_ITEM_COL_3, menuId);
            contentValues.put(ORDER_ITEM_COL_4, quantity - 1);

            result = db.insert(ORDER_ITEM_TABLE_NAME, null, contentValues);
        }

        if(result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public OrderItems getExistingOrderItem(Integer orderId, Integer menuId) {
        SQLiteDatabase db = this.getReadableDatabase();

        List<String> tableColumns = new ArrayList<String>();
        tableColumns.add("*");

        String whereClause = ORDER_ITEM_COL_2 +
                            " = ? AND " +
                            ORDER_ITEM_COL_3 +
                            " = ?";

        List<String> whereArgs = new ArrayList<String>();
        whereArgs.add(orderId.toString());
        whereArgs.add(menuId.toString());

        Cursor orderItemsCursor = db.query(ORDER_ITEM_TABLE_NAME, tableColumns.toArray(new String[tableColumns.size()]), whereClause, whereArgs.toArray(new String[whereArgs.size()]), null, null, null);

        if(orderItemsCursor.moveToFirst()) {
           return new OrderItems(orderItemsCursor.getInt(0), new Orders(orderItemsCursor.getInt(1), null), new Menu_Item(orderItemsCursor.getInt(2), null, null, null, null), orderItemsCursor.getInt(3));
        } else {
            return null;
        }
    }

    public List<OrderItems> getOrderItemByOrders(Integer orderId) {
        SQLiteDatabase db = this.getReadableDatabase();

        List<OrderItems> orderItemsToBeReturned = new ArrayList<OrderItems>();

        List<String> tableColumns = new ArrayList<String>();
        tableColumns.add("*");

        String whereClause = ORDER_ITEM_COL_2 + " = ?";

        List<String> whereArgs = new ArrayList<String>();
        whereArgs.add(orderId.toString());

        Cursor orderItemsCursor = db.query(ORDER_ITEM_TABLE_NAME, tableColumns.toArray(new String[tableColumns.size()]), whereClause, whereArgs.toArray(new String[whereArgs.size()]), null, null, null);

        while (orderItemsCursor.moveToNext()) {
            orderItemsToBeReturned.add(new OrderItems(orderItemsCursor.getInt(0), new Orders(orderItemsCursor.getInt(1), null), new Menu_Item(orderItemsCursor.getInt(2), null, null, null, null), orderItemsCursor.getInt(3)));
        }

        return orderItemsToBeReturned;
    }

    public Integer deleteOrderItem(Integer OrderItemId) {
        SQLiteDatabase db = this.getWritableDatabase();

        return db.delete(ORDER_ITEM_TABLE_NAME, ORDER_ITEM_COL_1 + " = ?", new String[] {Integer.toString(OrderItemId)});
    }
}
