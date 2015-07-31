package com.wen.database.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.wen.database.DatabaseUtilities;


/**
 * Created by w.gu on 7/29/2015.
 */
public class MenuDAO extends DatabaseUtilities {
    public MenuDAO(Context context) {
        super(context);
    }

    public boolean doesMenusExist() {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("select count(*) from " + MENU_ITEM_TABLE_NAME, null);
        cursor.moveToFirst();
        int cursorCount = cursor.getInt(0);

        if(cursorCount > 0) {
            return true;
        } else {
            return false;
        }
    }

    // Gets currently selected Menu
    public Integer getSelectedMenuId() {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("select " +
                MENU_ITEM_COL_1 +
                " from " +
                MENU_ITEM_TABLE_NAME +
                " where " +
                MENU_ITEM_COL_6 +
                " == 1"
                , null);

        cursor.moveToFirst();
        return cursor.getInt(0);
    }


}
