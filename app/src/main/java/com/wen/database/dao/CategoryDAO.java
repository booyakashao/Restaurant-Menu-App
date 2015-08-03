package com.wen.database.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.wen.database.DatabaseUtilities;

/**
 * Created by Wen on 8/2/2015.
 */
public class CategoryDAO extends DatabaseUtilities {

    public CategoryDAO(Context context) {
        super(context);
    }

    public boolean insertNewCategory(String newCategoryName) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(CATEGORY_COL_2, newCategoryName);

        long result = db.insert(CATEGORY_TABLE_NAME, null, contentValues);

        if(result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Integer deleteCategory(String category) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(CATEGORY_TABLE_NAME, CATEGORY_COL_2 + " = ?", new String[] {category});
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor res = db.rawQuery("select * from " + CATEGORY_TABLE_NAME, null);

        return res;
    }
}
