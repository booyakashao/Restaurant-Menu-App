package com.example.wen.foodmenuprinter;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


/*
* Remember to call finishActivity to return to previous activity
 */

/*
Remember to set current menu to the newly created menu
 */

public class CreateMenuItemActivity extends BaseActivityForCommon {


    EditText newMenuName;
    Button submitNewMenuButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_menu_item);

        newMenuName = (EditText) findViewById(R.id.name_for_new_menu_item);
        submitNewMenuButton = (Button) findViewById(R.id.create_menu_item_button);
        submitNewMenuButton.setOnClickListener(createMenuWithTextFieldOnClickListener());
    }

    private View.OnClickListener createMenuWithTextFieldOnClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //create Menu
            }
        };
    }
}
