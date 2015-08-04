package com.example.wen.foodmenuprinter;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.wen.database.dao.CategoryDAO;

import java.util.ArrayList;
import java.util.List;

public class DeleteCategoryActivity extends Activity {

    Spinner categorySelectorSpinner;
    Button deleteCategoryDialogButton;
    CategoryDAO categoryDAO;
    private static final int CategoryDeleteActivityResultCode = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_category);

        categoryDAO = new CategoryDAO(this);

        deleteCategoryDialogButton = (Button) findViewById(R.id.delete_category_dialog_button);
        deleteCategoryDialogButton.setOnClickListener(deleteCategoryOnClickListener());

        categorySelectorSpinner = (Spinner) findViewById(R.id.category_spinner_for_delete);
        categorySelectorSpinner.setAdapter(populateSpinner());
    }

    private ArrayAdapter<String> populateSpinner() {
        Cursor categoryCursor = categoryDAO.getAllData();

        List<String> category_spinner_string_list = new ArrayList<String>();
        if(categoryCursor.getCount() == 0) {
            category_spinner_string_list.add("No categories found to delete");
        } else {
            while(categoryCursor.moveToNext()) {
                category_spinner_string_list.add(categoryCursor.getString(1));
            }
        }

        ArrayAdapter<String> category_spinner_array_adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, category_spinner_string_list);

        category_spinner_array_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        return category_spinner_array_adapter;
    }

    private View.OnClickListener deleteCategoryOnClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(categoryDAO.deleteCategory(categorySelectorSpinner.getSelectedItem().toString()) >= 1) {
                    Toast.makeText(v.getContext(), "Category Deleted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(v.getContext(), "Category Failed to delete", Toast.LENGTH_SHORT).show();
                }
                executeDone();
            }
        };
    }

    @Override
    public void onBackPressed() {
        executeDone();
        super.onBackPressed();
    }

    private void executeDone() {
        Intent resultIntent = new Intent();
        resultIntent.putExtra("deletedCategory", DeleteCategoryActivity.this.categorySelectorSpinner.getSelectedItem().toString());
        setResult(RESULT_OK, resultIntent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Menu is disabled
        return false;
    }
}
