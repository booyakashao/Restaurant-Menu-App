package com.wen.database.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.wen.database.DatabaseUtilities;

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
