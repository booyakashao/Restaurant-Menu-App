package com.wen.database.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.wen.database.DatabaseUtilities;
import com.wen.database.model.Category;
import com.wen.database.model.Menu_Item;

import java.math.BigDecimal;
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
        SQLiteDatabase db = this.getReadableDatabase();

        List<String> tableColumns = new ArrayList<String>();
        tableColumns.add("count(*)");

        Cursor cursor =  db.query(MENU_ITEM_TABLE_NAME, tableColumns.toArray(new String[tableColumns.size()]), null, null, null, null, null);

        cursor.moveToFirst();
        int cursorCount = cursor.getInt(0);

        db.close();

        if(cursorCount > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean insertNewMenuItem(String name, String description, Integer categoryId, Double price) {
        SQLiteDatabase db = this.getWritableDatabase();

        Double priceToBeTruncated = price;
        Double truncatedPrice = new BigDecimal(priceToBeTruncated).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

        ContentValues contentValues = new ContentValues();
        contentValues.put(MENU_ITEM_COL_2, name);
        contentValues.put(MENU_ITEM_COL_3, description);
        contentValues.put(MENU_ITEM_COL_4, categoryId);
        contentValues.put(MENU_ITEM_COL_5, truncatedPrice);

        long result = db.insert(MENU_ITEM_TABLE_NAME, null, contentValues);

        db.close();

        if(result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Integer deleteMenuItemsForCategory(Integer categoryId) {
        SQLiteDatabase db = this.getWritableDatabase();
        Integer result = db.delete(MENU_ITEM_TABLE_NAME, MENU_ITEM_COL_4 + " = ?", new String[] {Integer.toString(categoryId)});

        db.close();

        return result;
    }

    public Integer deleteMenuItemById(Integer menuId) {
        SQLiteDatabase db = this.getWritableDatabase();
        Integer result = db.delete(MENU_ITEM_TABLE_NAME, MENU_ITEM_COL_1 + " = ?", new String[] {Integer.toString(menuId)});

        db.close();

        return result;
    }

    public Menu_Item getMenuItemById(Integer id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Menu_Item currentMenuItem = new Menu_Item();

        List<String> tableColumns = new ArrayList<String>();
        tableColumns.add("*");

        String whereClause = MENU_ITEM_COL_1 + " = ?";

        List<String> whereArgs = new ArrayList<String>();
        whereArgs.add(Integer.toString(id));

        Cursor menuItemCursor = db.query(MENU_ITEM_TABLE_NAME, tableColumns.toArray(new String[tableColumns.size()]), whereClause, whereArgs.toArray(new String[whereArgs.size()]), null, null, null);

        if (menuItemCursor.moveToFirst()) {
            currentMenuItem = new Menu_Item(menuItemCursor.getInt(0), menuItemCursor.getString(1), menuItemCursor.getString(2), menuItemCursor.getDouble(4), new Category(menuItemCursor.getInt(3), null));
        }

        db.close();

        return currentMenuItem;
    }

    public List<Menu_Item> getMenuItemsByCategoryId(Integer categoryId) {
        SQLiteDatabase db = this.getReadableDatabase();

        List<Menu_Item> listOfAllMenuItems = new ArrayList<Menu_Item>();

        List<String> tableColumns = new ArrayList<String>();
        tableColumns.add("*");

        String whereClause = MENU_ITEM_COL_4 + " = ?";

        List<String> whereArgs = new ArrayList<String>();
        whereArgs.add(categoryId.toString());

        Cursor menuItemCursor = db.query(MENU_ITEM_TABLE_NAME, tableColumns.toArray(new String[tableColumns.size()]), whereClause, whereArgs.toArray(new String[whereArgs.size()]), null, null, null);

        if (menuItemCursor.moveToFirst()) {
            listOfAllMenuItems.add(new Menu_Item(menuItemCursor.getInt(0), menuItemCursor.getString(1), menuItemCursor.getString(2), menuItemCursor.getDouble(4), new Category(categoryId, null)));
        }

        db.close();

        return listOfAllMenuItems;
    }

    // Not Used
    // Gets currently selected Menu
    public Integer getSelectedMenuId() {
        SQLiteDatabase db = this.getReadableDatabase();

        List<String> tableColumns = new ArrayList<String>();
        tableColumns.add("*");

        Cursor cursor =  db.query(MENU_ITEM_TABLE_NAME, tableColumns.toArray(new String[tableColumns.size()]), null, null, null, null, null);

        cursor.moveToFirst();

        db.close();

        return cursor.getInt(0);
    }

    public boolean updateMenu(Menu_Item toBeUpdateMenuItem) {
        SQLiteDatabase db = this.getWritableDatabase();

        long result = 0;

        ContentValues contentValues = new ContentValues();
        contentValues.put(MENU_ITEM_COL_1, toBeUpdateMenuItem.getId());
        contentValues.put(MENU_ITEM_COL_2, toBeUpdateMenuItem.getName());
        contentValues.put(MENU_ITEM_COL_3, toBeUpdateMenuItem.getDescription());
        contentValues.put(MENU_ITEM_COL_4, toBeUpdateMenuItem.getCategory().getId());
        contentValues.put(MENU_ITEM_COL_5, toBeUpdateMenuItem.getPrice());

        String whereClause = MENU_ITEM_COL_1 + " = ? ";

        List<String> whereArgs = new ArrayList<String>();
        whereArgs.add(toBeUpdateMenuItem.getId().toString());

        result = db.update(MENU_ITEM_TABLE_NAME, contentValues, whereClause, whereArgs.toArray(new String[whereArgs.size()]));

        db.close();

        if(result == -1) {
            return false;
        } else {
            return true;
        }
    }
}
