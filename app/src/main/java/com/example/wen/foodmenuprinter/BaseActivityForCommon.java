package com.example.wen.foodmenuprinter;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.wen.database.dao.CategoryDAO;
import com.wen.database.dao.MenuDAO;
import com.wen.database.dao.OrderItemsDAO;
import com.wen.database.dao.OrdersDAO;
import com.wen.database.dao.TaxRateDAO;

/**
 * Created by w.gu on 7/29/2015.
 */
public class BaseActivityForCommon extends AppCompatActivity {

    protected CategoryDAO categoryDAO;
    protected MenuDAO menuDAO;
    protected OrderItemsDAO orderItemsDAO;
    protected OrdersDAO ordersDAO;
    protected TaxRateDAO taxRateDAO;

    protected static final int CategoryEntryActivityResultCode = 1;
    protected static final int CategoryDeleteActivityResultCode = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Set up DAOs
        categoryDAO = new CategoryDAO(this);
        menuDAO = new MenuDAO(this);
        orderItemsDAO = new OrderItemsDAO(this);
        ordersDAO = new OrdersDAO(this);
        taxRateDAO = new TaxRateDAO(this);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        //Removes open menu option if menu items don't exist
        MenuItem currentItem = menu.findItem(R.id.open_menu_option);
        if(!menuDAO.doesMenusExist()) {

            currentItem.setVisible(false);
        } else {
            currentItem.setVisible(true);
        }

        return super.onPrepareOptionsMenu(menu);
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
            case R.id.open_menu_option:
                Intent openMenuActivity = new Intent(this, CategoryListActivity.class);
                this.startActivity(openMenuActivity);
            case R.id.edit_menu_option:
                Toast.makeText(this, "Edit menu Option selected", Toast.LENGTH_LONG).show();
                break;
            case R.id.delete_menu_option:
                Toast.makeText(this, "Delete Menu Option Selected", Toast.LENGTH_LONG).show();
                break;
            case R.id.main_menu_option:
                Intent mainMenuActivity = new Intent(this, MainMenuActivity.class);
                this.startActivity(mainMenuActivity);
                break;
            default:
                Toast.makeText(this, "Cannot recognize this option", Toast.LENGTH_LONG).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
