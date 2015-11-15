package com.example.wen.foodmenuprinter.openMenuModals;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.wen.foodmenuprinter.CommonUtils;
import com.example.wen.foodmenuprinter.R;
import com.wen.database.dao.CategoryDAO;
import com.wen.database.dao.MenuDAO;
import com.wen.database.model.Category;
import com.wen.database.model.Menu_Item;

public class EditItem extends Activity {

    MenuDAO menuDAO;
    CategoryDAO categoryDAO;

    EditText title;
    EditText description;
    EditText price;
    Spinner categorySpinner;
    Button submitButton;

    Menu_Item currentMenuItem;

    Intent intentData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        menuDAO = new MenuDAO(this);
        categoryDAO = new CategoryDAO(this);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND,
                WindowManager.LayoutParams.FLAG_DIM_BEHIND);

        intentData = getIntent();

        title = (EditText) findViewById(R.id.titleEditText);
        description = (EditText) findViewById(R.id.descriptionEditText);
        price = (EditText) findViewById(R.id.priceEditText);
        categorySpinner = (Spinner) findViewById(R.id.categorySpinner);

        submitButton = (Button) findViewById(R.id.editItemButton);
        submitButton.setOnClickListener(submitMenuChangesOnClickListener());

        populateEditMenuItemParamters();
    }

    private void populateEditMenuItemParamters() {
        Integer menu_item_id = intentData.getIntExtra("menu_item_id", -1);

        currentMenuItem = menuDAO.getMenuItemById(menu_item_id);
        title.setText(currentMenuItem.getName());
        description.setText(currentMenuItem.getDescription());
        price.setText(currentMenuItem.getPrice().toString());
        currentMenuItem.setCategory(categoryDAO.getCategoryById(currentMenuItem.getCategory().getId()));


        ArrayAdapter<String> categorySpinnerAdapter = CommonUtils.populateCategorySpinner(this);
        categorySpinner.setAdapter(categorySpinnerAdapter);

        if (!currentMenuItem.getCategory().getName().equals(null)) {
            int spinnerPosition = categorySpinnerAdapter.getPosition(currentMenuItem.getCategory().getName());
            categorySpinner.setSelection(spinnerPosition);
        }
    }

    private View.OnClickListener submitMenuChangesOnClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentMenuItem.setName(title.getText().toString());
                currentMenuItem.setDescription(description.getText().toString());

                Integer categoryId = categoryDAO.getCategoryByName(categorySpinner.getSelectedItem().toString());
                Category selectedCategory = categoryDAO.getCategoryById(categoryId);

                currentMenuItem.setCategory(selectedCategory);
                if(!price.getText().toString().isEmpty()) {
                    currentMenuItem.setPrice(Double.parseDouble(price.getText().toString()));
                }

                if(menuDAO.updateMenu(currentMenuItem)) {
                    Toast.makeText(v.getContext(), "Menu Item was updated", Toast.LENGTH_SHORT).show();
                    executeDone();
                } else {
                    Toast.makeText(v.getContext(), "Menu Item failed to updated", Toast.LENGTH_SHORT).show();
                }
            }
        };
    }

    @Override
    public void onBackPressed() {
        executeDone();
        super.onBackPressed();
    }

    private void executeDone() {
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Menu is disabled for this
        return false;
    }
}
