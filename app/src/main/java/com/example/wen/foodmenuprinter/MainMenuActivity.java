package com.example.wen.foodmenuprinter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.wen.database.dao.MenuDAO;


public class MainMenuActivity extends AppCompatActivity {


    MenuDAO menuDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        //Set up DAOs
        menuDAO = new MenuDAO(this);

        if(menuDAO.doesMenusExist()) {
            Toast.makeText(MainMenuActivity.this, "Menu Exists", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(MainMenuActivity.this, "Menu Does Not Exists", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
