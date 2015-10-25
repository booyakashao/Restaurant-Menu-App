package com.wen.database.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.wen.database.DatabaseUtilities;
import com.wen.database.model.Menu_Item;
import com.wen.database.model.OrderItems;
import com.wen.database.model.Orders;
import com.wen.database.model.TaxRate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wen on 9/18/2015.
 */
public class TaxRateDAO extends DatabaseUtilities {
    public TaxRateDAO(Context context) {
        super(context);
    }

    public boolean setOrCreateTaxRate(Double newTaxRate) {
        SQLiteDatabase db = this.getWritableDatabase();

        long result = 0;

        TaxRate existingTaxRate = hasExistingTaxRate();

        if(existingTaxRate != null) {
            existingTaxRate.setTaxRate(newTaxRate);

            ContentValues contentValues = new ContentValues();
            contentValues.put(TAX_RATE_COL_2, existingTaxRate.getTaxRate());

            String whereClause = TAX_RATE_COL_1 + " = ? ";

            List<String> whereArgs = new ArrayList<String>();
            whereArgs.add(existingTaxRate.getId().toString());

            result = db.update(TAX_RATE_TABLE_NAME, contentValues, whereClause, whereArgs.toArray(new String[whereArgs.size()]));
        } else {
            ContentValues contentValues = new ContentValues();
            contentValues.put(TAX_RATE_COL_2, newTaxRate);

            result = db.insert(TAX_RATE_TABLE_NAME, null, contentValues);
        }

        if(result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public TaxRate hasExistingTaxRate() {
        SQLiteDatabase db = this.getReadableDatabase();

        List<String> tableColumns = new ArrayList<String>();
        tableColumns.add("*");

        List<String> whereArgs = new ArrayList<String>();

        Cursor taxRateCursor = db.query(TAX_RATE_TABLE_NAME, tableColumns.toArray(new String[tableColumns.size()]), "", whereArgs.toArray(new String[whereArgs.size()]), null, null, null);

        if(taxRateCursor.moveToFirst()) {
            return new TaxRate(taxRateCursor.getInt(0), taxRateCursor.getDouble(1));
        } else {
            return null;
        }
    }

    public TaxRate getTaxRate() {
        TaxRate currentTaxRate = hasExistingTaxRate();
        if(currentTaxRate != null) {
            return currentTaxRate;
        } else {
            return new TaxRate(0, 0.00);
        }
    }
}
