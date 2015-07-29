package com.example.wen.foodmenuprinter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.wen.database.dao.MenuDAO;


/*
* Remember to call finishActivity to return to previous activity
 */

/*
Remember to set current menu to the newly created menu
 */

public class CreateMenuActivity extends BaseActivityForCommon {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_menu);
    }
}
