package com.example.wen.foodmenuprinter;

import android.app.ActionBar;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.wen.database.dao.MenuDAO;


public class MainMenuActivity extends AppCompatActivity {


    MenuDAO menuDAO;
    RelativeLayout mainActivityLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        mainActivityLayout = (RelativeLayout) findViewById(R.id.main_menu_layout);

        //Set up DAOs
        menuDAO = new MenuDAO(this);

        if(menuDAO.doesMenusExist()) {
            Button openMenuButton = new Button(this);
            openMenuButton.setText(R.string.open_menu_button_text);
            openMenuButton.setTag("openMenuButton");

            RelativeLayout.LayoutParams openMenuButtonParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);
            openMenuButtonParams.addRule(RelativeLayout.CENTER_VERTICAL);
            openMenuButtonParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
            openMenuButton.setLayoutParams(openMenuButtonParams);

            mainActivityLayout.addView(openMenuButton);
        } else {
            Button createMenuButton = new Button(this);
            createMenuButton.setText(R.string.create_menu_button_text);
            createMenuButton.setTag("createMenuButton");

            RelativeLayout.LayoutParams createMenuButtonParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);
            createMenuButtonParams.addRule(RelativeLayout.CENTER_VERTICAL);
            createMenuButtonParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
            createMenuButton.setLayoutParams(createMenuButtonParams);

            createMenuButton.setOnClickListener(createMenuOnClickListener());

            mainActivityLayout.addView(createMenuButton);
        }
    }

    private View.OnClickListener createMenuOnClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent createMenuActivity = new Intent(MainMenuActivity.this, CreateMenuActivity.class);
                MainMenuActivity.this.startActivity(createMenuActivity);
            }
        };
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

        if(id == R.id.create_new_menu_option) {
            Intent createMenuActivity = new Intent(MainMenuActivity.this, CreateMenuActivity.class);
            MainMenuActivity.this.startActivity(createMenuActivity);
        }

        return super.onOptionsItemSelected(item);
    }
}
