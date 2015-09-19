package com.example.wen.foodmenuprinter.createMenuModals;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.wen.foodmenuprinter.R;
import com.wen.database.dao.CategoryDAO;

public class CategoryEntryActivity extends Activity {

    EditText categoryTextEntry;
    Button AddCategoryDialogButton;
    CategoryDAO categoryDAO;
    private static final int CategoryEntryActivityResultCode = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_entry);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND,
                WindowManager.LayoutParams.FLAG_DIM_BEHIND);

        categoryDAO = new CategoryDAO(this);

        try {
            categoryTextEntry = (EditText) findViewById(R.id.editNewCategoryText);
        } catch (Exception e) {
        }

        try {
            AddCategoryDialogButton = (Button) findViewById(R.id.add_category_dialog_button);
        } catch (Exception e) {
        }

        AddCategoryDialogButton.setOnClickListener(addCategoryOnClickListener());
    }

    private View.OnClickListener addCategoryOnClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(categoryDAO.insertNewCategory(categoryTextEntry.getText().toString())) {
                    Toast.makeText(v.getContext(), "Category Added", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(v.getContext(), "Category Failed to add", Toast.LENGTH_SHORT).show();
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
        resultIntent.putExtra("newCategory", CategoryEntryActivity.this.categoryTextEntry.getText().toString());
        setResult(RESULT_OK, resultIntent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Menu is disabled for this
        return false;
    }
}
