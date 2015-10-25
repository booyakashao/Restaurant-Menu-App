package com.example.wen.foodmenuprinter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;


public class MainMenuActivity extends BaseActivityForCommon {

    LinearLayout mainActivityLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        mainActivityLayout = (LinearLayout) findViewById(R.id.main_menu_layout);

        Button createMenuButton = new Button(this);
        createMenuButton.setText(R.string.create_menu_button_text);
        createMenuButton.setId(R.id.create_menu_button);
        createMenuButton.setTag("createMenuButton");
        createMenuButton.setPadding(20, 20, 0, 0);

        createMenuButton.setOnClickListener(createMenuOnClickListener());

        mainActivityLayout.addView(createMenuButton);

        if(menuDAO.doesMenusExist()) {
            Button openMenuButton = new Button(this);
            openMenuButton.setText(R.string.open_menu_button_text);
            openMenuButton.setId(R.id.open_menu_button);
            openMenuButton.setTag("openMenuButton");
            openMenuButton.setPadding(20, 20, 0, 0);

            openMenuButton.setOnClickListener(openMenuOnClickListener());

            mainActivityLayout.addView(openMenuButton);
        }

        Button changeTaxRate = new Button(this);
        changeTaxRate.setText(R.string.change_tax_rate_button_text);
        changeTaxRate.setId(R.id.change_tax_rate);
        changeTaxRate.setTag("changeTaxRateButton");
        changeTaxRate.setPadding(20, 20, 0, 0);
        changeTaxRate.setOnClickListener(changeTaxRateOnClickListener());

        mainActivityLayout.addView(changeTaxRate);
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

    private View.OnClickListener openMenuOnClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openMenuActivity = new Intent(MainMenuActivity.this, CategoryListActivity.class);
                MainMenuActivity.this.startActivity(openMenuActivity);

                if(!ordersDAO.hasCurrentOrder()) {
                    Toast.makeText(v.getContext(), "Current Order does not exist.", Toast.LENGTH_SHORT).show();
                    if(ordersDAO.createNewOrder()) {
                        Toast.makeText(v.getContext(), "New order created.", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(v.getContext(), "Failed to create new order", Toast.LENGTH_SHORT).show();
                    }
                }

                finish();
            }
        };
    }

    private View.OnClickListener changeTaxRateOnClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent changeTaxRateActivity = new Intent(MainMenuActivity.this, ChangeTaxRateActivity.class);
                MainMenuActivity.this.startActivity(changeTaxRateActivity);
                finish();
            }
        };
    }
}
