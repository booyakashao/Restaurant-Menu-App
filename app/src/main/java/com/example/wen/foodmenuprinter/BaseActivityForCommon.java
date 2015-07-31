package com.example.wen.foodmenuprinter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.wen.database.dao.MenuDAO;

/**
 * Created by w.gu on 7/29/2015.
 */
public class BaseActivityForCommon extends AppCompatActivity {

    protected MenuDAO menuDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Set up DAOs
        menuDAO = new MenuDAO(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.base_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch(id) {
            case R.id.create_new_menu_option:
                Intent createMenuItemActivity = new Intent(this, CreateMenuItemActivity.class);
                this.startActivity(createMenuItemActivity);
                break;
            case R.id.edit_menu_option:
                Toast.makeText(this, "Edit menu Option selected", Toast.LENGTH_LONG).show();
                break;
            case R.id.delete_menu_option:
                Toast.makeText(this, "Delete Menu Option Selected", Toast.LENGTH_LONG).show();
                break;
            default:
                Toast.makeText(this, "Cannot recognize this option", Toast.LENGTH_LONG).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
