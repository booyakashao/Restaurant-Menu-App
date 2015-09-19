package com.example.wen.foodmenuprinter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;

public class ViewCartActivity extends BaseActivityForCommon {

    ExpandableListView listOfOrdersByCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_cart);

        listOfOrdersByCategory  = (ExpandableListView) findViewById(R.id.expandableListView);
    }
}
