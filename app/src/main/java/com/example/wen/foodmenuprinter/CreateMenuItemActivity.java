package com.example.wen.foodmenuprinter;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/*
* Remember to call finishActivity to return to previous activity
 */

/*
Remember to set current menu to the newly created menu
 */

public class CreateMenuItemActivity extends BaseActivityForCommon {


    EditText newMenuName;
    Button submitNewMenuButton;
    Button createNewCategoryButton;
    Button deleteCategoryButton;
    Spinner categorySelectorSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_menu_item);

        newMenuName = (EditText) findViewById(R.id.name_for_new_menu_item);
        submitNewMenuButton = (Button) findViewById(R.id.create_menu_item_button);
        submitNewMenuButton.setOnClickListener(createMenuOnClickListener());
        createNewCategoryButton = (Button) findViewById(R.id.add_category_button);
        createNewCategoryButton.setOnClickListener(createCategoryOnClickListener());
        deleteCategoryButton = (Button) findViewById(R.id.delete_category_button);
        deleteCategoryButton.setOnClickListener(deleteCategoryOnClickListener());

        //Set up category spinner
        categorySelectorSpinner = (Spinner) findViewById(R.id.category_spinner);
        categorySelectorSpinner.setAdapter(populateSpinner());
    }

    private ArrayAdapter<String> populateSpinner() {
        Cursor categoryCursor = categoryDAO.getAllData();

        List<String> category_spinner_string_list = new ArrayList<String>();
        if(categoryCursor.getCount() == 0) {
            category_spinner_string_list.add("Add a category");
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


    private View.OnClickListener createMenuOnClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //create Menu
            }
        };
    }

    private View.OnClickListener createCategoryOnClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addCategoryEntryIntent = new Intent(v.getContext(), CategoryEntryActivity.class);
                v.getContext().startActivity(addCategoryEntryIntent);

            }
        };
    }

    private View.OnClickListener deleteCategoryOnClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent deleteCategoryEntryIntent = new Intent(v.getContext(), DeleteCategoryActivity.class);
                v.getContext().startActivity(deleteCategoryEntryIntent);

            }
        };
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(requestCode) {
            case CategoryEntryActivityResultCode:
                categorySelectorSpinner.setAdapter(populateSpinner());
                break;
            case CategoryDeleteActivityResultCode:
                categorySelectorSpinner.setAdapter(populateSpinner());
                break;
            default:
                Toast.makeText(this, "Activity not recognized", Toast.LENGTH_SHORT).show();
        }
    }
}
