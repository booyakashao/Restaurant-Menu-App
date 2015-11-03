package com.example.wen.foodmenuprinter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.wen.foodmenuprinter.createMenuModals.CategoryEntryActivity;
import com.example.wen.foodmenuprinter.createMenuModals.DeleteCategoryActivity;
import com.wen.database.model.Category;

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
    EditText newMenuDescription;
    EditText newMenuPrice;
    Button submitNewMenuButton;
    Button createNewCategoryButton;
    Button deleteCategoryButton;
    Spinner categorySelectorSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_menu_item);

        newMenuName = (EditText) findViewById(R.id.new_menu_item_name);
        newMenuDescription = (EditText) findViewById(R.id.new_menu_item_description);
        newMenuPrice = (EditText) findViewById(R.id.new_menu_item_price);
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
        List<Category> allCategories = categoryDAO.getAllCategories();

        List<String> category_spinner_string_list = new ArrayList<String>();
        if(allCategories.size() == 0) {
            category_spinner_string_list.add("Add a category");
        } else {
            for(Category currentCategory : allCategories) {
                category_spinner_string_list.add(currentCategory.getName());
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
                String name = newMenuName.getText().toString();
                String description = newMenuDescription.getText().toString();
                String categoryName = categorySelectorSpinner.getSelectedItem().toString();
                Double price = Double.parseDouble(newMenuPrice.getText().toString());
                Integer categoryId = categoryDAO.getCategoryByName(categoryName);

                if(menuDAO.insertNewMenuItem(name, description, categoryId, price)) {
                    Toast.makeText(v.getContext(), "Menu item was created", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(v.getContext(), "Menu item was not created", Toast.LENGTH_LONG).show();
                }

                Intent mainMenuIntent = new Intent(v.getContext(), MainMenuActivity.class);
                startActivity(mainMenuIntent);
                finish();
            }
        };
    }

    @Override
    public void onBackPressed() {
        Intent mainMenuIntent = new Intent(this, MainMenuActivity.class);
        startActivity(mainMenuIntent);
        finish();
        super.onBackPressed();
    }

    private View.OnClickListener createCategoryOnClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addCategoryEntryIntent = new Intent(v.getContext(), CategoryEntryActivity.class);
                startActivityForResult(addCategoryEntryIntent, CategoryEntryActivityResultCode);

            }
        };
    }

    private View.OnClickListener deleteCategoryOnClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent deleteCategoryEntryIntent = new Intent(v.getContext(), DeleteCategoryActivity.class);
                startActivityForResult(deleteCategoryEntryIntent, CategoryDeleteActivityResultCode);

            }
        };
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(requestCode) {
            case CategoryEntryActivityResultCode:
                if (resultCode == RESULT_OK) {
                    categorySelectorSpinner.setAdapter(populateSpinner());
                }
                break;
            case CategoryDeleteActivityResultCode:
                if (resultCode == RESULT_OK) {
                    categorySelectorSpinner.setAdapter(populateSpinner());
                }
                break;
            default:
                Toast.makeText(this, "Activity not recognized", Toast.LENGTH_SHORT).show();
        }
    }
}
