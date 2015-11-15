package com.example.wen.foodmenuprinter;

import android.app.Activity;
import android.content.Context;
import android.print.PrintAttributes;
import android.print.PrintManager;
import android.widget.ArrayAdapter;

import com.wen.database.dao.CategoryDAO;
import com.wen.database.model.Category;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wen on 10/5/2015.
 */
public class CommonUtils {

    public static CategoryDAO categoryDAO;

    public static String convertDoubleToPrice(Double price) {

        return "$ " + new DecimalFormat("#0.00").format(price);
    }

    public static String convertToPercentage(Double percentage) {
        return new DecimalFormat("#0.00").format(percentage);
    }

    public static void doPrint(Activity targetActivity, List<String> documentContents) {
        PrintManager printManager = (PrintManager) targetActivity.getSystemService(Context.PRINT_SERVICE);

        // Set job name, which will be displayed in the print queue
        String jobName = targetActivity.getString(R.string.app_name) + " Receipt";

        // Start a print job, passing in a PrintDocumentAdapter implementation
        // to handle the generation of a print document
        printManager.print(jobName, new MyPrintDocumentAdapter(targetActivity, documentContents), null);
    }

    public static ArrayAdapter<String> populateCategorySpinner(Activity currentActivity) {
        if(categoryDAO == null) {
            categoryDAO = new CategoryDAO(currentActivity);
        }

        List<Category> allCategories = categoryDAO.getAllCategories();

        List<String> category_spinner_string_list = new ArrayList<String>();
        if(allCategories.size() == 0) {
            category_spinner_string_list.add("Add a category");
        } else {
            for(Category currentCategory : allCategories) {
                category_spinner_string_list.add(currentCategory.getName());
            }
        }

        ArrayAdapter<String> category_spinner_array_adapter = new ArrayAdapter<String>(currentActivity,
                android.R.layout.simple_spinner_item, category_spinner_string_list);

        category_spinner_array_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        return category_spinner_array_adapter;
    }
}
