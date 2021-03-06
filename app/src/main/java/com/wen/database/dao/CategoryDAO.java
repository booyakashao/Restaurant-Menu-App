package com.wen.database.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.wen.database.DatabaseUtilities;
import com.wen.database.model.Category;

import java.util.ArrayList;
import java.util.List;

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

        db.close();

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

    public Category getCategoryById(Integer id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Category categoryToReturn = new Category();

        List<String> tableColumns = new ArrayList<String>();
        tableColumns.add("*");

        String whereClause = CATEGORY_COL_1 + " = ?";

        List<String> whereArgs = new ArrayList<String>();
        whereArgs.add(Integer.toString(id));

        Cursor cursor =  db.query(CATEGORY_TABLE_NAME, tableColumns.toArray(new String[tableColumns.size()]), whereClause, whereArgs.toArray(new String[whereArgs.size()]), null, null, null);

        if(cursor.moveToFirst()) {
            categoryToReturn = new Category(cursor.getInt(0), cursor.getString(1));
        }

        db.close();

        return categoryToReturn;
    }

    public Integer getCategoryByName(String categoryName) {
        SQLiteDatabase db = this.getReadableDatabase();

        List<String> tableColumns = new ArrayList<String>();
        tableColumns.add("id");

        String whereClause = CATEGORY_COL_2 + " = ?";

        List<String> whereArgs = new ArrayList<String>();
        whereArgs.add(categoryName);

        Cursor cursor =  db.query(CATEGORY_TABLE_NAME, tableColumns.toArray(new String[tableColumns.size()]), whereClause, whereArgs.toArray(new String[whereArgs.size()]), null, null, null);
        cursor.moveToFirst();

        db.close();

        return cursor.getInt(0);
    }

    public List<Category> getAllCategories() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Category> categoriesToReturn = new ArrayList<Category>();

        List<String> tableColumns = new ArrayList<String>();
        tableColumns.add("*");

        Cursor categoryCursor = db.query(CATEGORY_TABLE_NAME, tableColumns.toArray(new String[tableColumns.size()]), null, null, null, null, null);
        while (categoryCursor.moveToNext()) {
            categoriesToReturn.add(new Category(categoryCursor.getInt(0), categoryCursor.getString(1)));
        }

        db.close();

        return categoriesToReturn;
    }
}
