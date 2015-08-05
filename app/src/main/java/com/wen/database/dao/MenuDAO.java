package com.wen.database.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.wen.database.DatabaseUtilities;
import com.wen.database.model.Category;
import com.wen.database.model.Menu_Item;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by w.gu on 7/29/2015.
 */
public class MenuDAO extends DatabaseUtilities {
    public MenuDAO(Context context) {
        super(context);
    }

    public boolean doesMenusExist() {
        SQLiteDatabase db = this.getWritableDatabase();

        List<String> tableColumns = new ArrayList<String>();
        tableColumns.add("count(*)");

        Cursor cursor =  db.query(MENU_ITEM_TABLE_NAME, tableColumns.toArray(new String[tableColumns.size()]), null, null, null, null, null);

        cursor.moveToFirst();
        int cursorCount = cursor.getInt(0);

        if(cursorCount > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean insertNewMenuItem(String name, String description, Integer categoryId, Double price) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(MENU_ITEM_COL_2, name);
        contentValues.put(MENU_ITEM_COL_3, description);
        contentValues.put(MENU_ITEM_COL_4, categoryId);
        contentValues.put(MENU_ITEM_COL_5, price);

        long result = db.insert(MENU_ITEM_TABLE_NAME, null, contentValues);

        if(result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public List<Menu_Item> getMenuItemsByCategoryId(Integer categoryId) {
        SQLiteDatabase db = this.getWritableDatabase();

        List<Menu_Item> listOfAllMenuItems = new ArrayList<Menu_Item>();

        List<String> tableColumns = new ArrayList<String>();
        tableColumns.add("*");

        String whereClause = MENU_ITEM_COL_4 + " = ?";

        List<String> whereArgs = new ArrayList<String>();
        whereArgs.add(categoryId.toString());

        Cursor menuItemCursor = db.query(MENU_ITEM_TABLE_NAME, tableColumns.toArray(new String[tableColumns.size()]), whereClause, whereArgs.toArray(new String[whereArgs.size()]), null, null, null);

        while (menuItemCursor.moveToNext()) {
            listOfAllMenuItems.add(new Menu_Item(menuItemCursor.getInt(0), menuItemCursor.getString(1), menuItemCursor.getString(2), menuItemCursor.getDouble(3), new Category(categoryId, null)));
        }

        return listOfAllMenuItems;
    }

    // Not Used
    // Gets currently selected Menu
    public Integer getSelectedMenuId() {
        SQLiteDatabase db = this.getWritableDatabase();

        List<String> tableColumns = new ArrayList<String>();
        tableColumns.add("*");

        Cursor cursor =  db.query(MENU_ITEM_TABLE_NAME, tableColumns.toArray(new String[tableColumns.size()]), null, null, null, null, null);

        cursor.moveToFirst();
        return cursor.getInt(0);
    }


}
