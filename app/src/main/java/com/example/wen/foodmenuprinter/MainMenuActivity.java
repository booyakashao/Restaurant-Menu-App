package com.example.wen.foodmenuprinter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;


public class MainMenuActivity extends BaseActivityForCommon {

    RelativeLayout mainActivityLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        mainActivityLayout = (RelativeLayout) findViewById(R.id.main_menu_layout);

        Button createMenuButton = new Button(this);
        createMenuButton.setText(R.string.create_menu_button_text);
        createMenuButton.setId(R.id.create_menu_button);
        createMenuButton.setTag("createMenuButton");

        RelativeLayout.LayoutParams createMenuButtonParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        createMenuButtonParams.addRule(RelativeLayout.ALIGN_LEFT);
        createMenuButtonParams.addRule(RelativeLayout.CENTER_VERTICAL);
        createMenuButtonParams.setMargins(190, 0, 0, 0);
        createMenuButton.setLayoutParams(createMenuButtonParams);

        createMenuButton.setOnClickListener(createMenuOnClickListener());

        mainActivityLayout.addView(createMenuButton);

        if(menuDAO.doesMenusExist()) {
            Button openMenuButton = new Button(this);
            openMenuButton.setText(R.string.open_menu_button_text);
            openMenuButton.setId(R.id.open_menu_button);
            openMenuButton.setTag("openMenuButton");

            RelativeLayout.LayoutParams openMenuButtonParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);
            openMenuButtonParams.addRule(RelativeLayout.RIGHT_OF, createMenuButton.getId());
            openMenuButtonParams.addRule(RelativeLayout.END_OF, createMenuButton.getId());
            openMenuButtonParams.addRule(RelativeLayout.CENTER_VERTICAL);
            openMenuButton.setLayoutParams(openMenuButtonParams);

            mainActivityLayout.addView(openMenuButton);
        }
    }

    private View.OnClickListener createMenuOnClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent createMenuActivity = new Intent(MainMenuActivity.this, CreateMenuItemActivity.class);
                MainMenuActivity.this.startActivity(createMenuActivity);
                finish();
            }
        };
    }
}
