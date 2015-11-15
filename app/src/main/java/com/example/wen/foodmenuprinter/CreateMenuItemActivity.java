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
        categorySelectorSpinner.setAdapter(CommonUtils.populateCategorySpinner(this));
    }

    private View.OnClickListener createMenuOnClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(categoryDAO.getAllCategories().isEmpty()) {
                    Toast.makeText(v.getContext(), "Please create and select a category before continuing", Toast.LENGTH_LONG).show();
                } if(newMenuPrice.getText().toString().isEmpty()) {
                    Toast.makeText(v.getContext(), "Please enter a price", Toast.LENGTH_LONG).show();
                } else {
                    String name = newMenuName.getText().toString();
                    String description = newMenuDescription.getText().toString();
                    String categoryName = categorySelectorSpinner.getSelectedItem().toString();
                    Double price = Double.parseDouble(newMenuPrice.getText().toString());
                    Integer categoryId = categoryDAO.getCategoryByName(categoryName);

                    if(menuDAO.insertNewMenuItem(name, description, categoryId, price)) {
                        //Toast.makeText(v.getContext(), "Menu item was created", Toast.LENGTH_LONG).show();
                    } else {
                        //Toast.makeText(v.getContext(), "Menu item was not created", Toast.LENGTH_LONG).show();
                    }

                    Intent mainMenuIntent = new Intent(v.getContext(), MainMenuActivity.class);
                    startActivity(mainMenuIntent);
                }
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
                    categorySelectorSpinner.setAdapter(CommonUtils.populateCategorySpinner(this));
                }
                break;
            case CategoryDeleteActivityResultCode:
                if (resultCode == RESULT_OK) {
                    categorySelectorSpinner.setAdapter(CommonUtils.populateCategorySpinner(this));
                }
                break;
            default:
                Toast.makeText(this, "Activity not recognized", Toast.LENGTH_SHORT).show();
        }
    }
}
