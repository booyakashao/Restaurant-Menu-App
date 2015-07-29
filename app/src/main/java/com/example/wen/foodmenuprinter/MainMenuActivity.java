package com.example.wen.foodmenuprinter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.wen.database.dao.MenuDAO;


public class MainMenuActivity extends BaseActivityForCommon {

    RelativeLayout mainActivityLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        mainActivityLayout = (RelativeLayout) findViewById(R.id.main_menu_layout);

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
}
