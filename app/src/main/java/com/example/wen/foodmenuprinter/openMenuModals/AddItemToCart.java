package com.example.wen.foodmenuprinter.openMenuModals;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.example.wen.foodmenuprinter.R;
import com.wen.database.dao.MenuDAO;
import com.wen.database.dao.OrdersDAO;
import com.wen.database.model.Orders;

public class AddItemToCart extends Activity {

    MenuDAO menuDAO;
    OrdersDAO orderDAO;
    NumberPicker quantityNumberPicker;
    Button addToCartButton;
    Intent indentData;
    Orders currentOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item_to_cart);

        // //Set up DAOs
        menuDAO = new MenuDAO(this);
        orderDAO = new OrdersDAO(this);

        addToCartButton = (Button) findViewById(R.id.addToCartButton);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND,
                WindowManager.LayoutParams.FLAG_DIM_BEHIND);

        quantityNumberPicker = (NumberPicker) findViewById(R.id.quantityNumberPicker);

        quantityNumberPicker.setMinValue(0);
        quantityNumberPicker.setMaxValue(999);
        quantityNumberPicker.setWrapSelectorWheel(false);

        addToCartButton.setOnClickListener(addToCartOnClickListener());

        indentData = getIntent();
    }

    private View.OnClickListener addToCartOnClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform add to cart function here

                Integer menu_item_id = indentData.getIntExtra("menu_item_id", -1);

                // Toast.makeText(v.getContext(), "Current menu_item_id is " + menu_item_id, Toast.LENGTH_SHORT).show();
//                 Toast.makeText(v.getContext(), "You have added " + quantityNumberPicker.getValue() + " items.", Toast.LENGTH_SHORT).show();
                boolean hasCurrentOrder = orderDAO.hasCurrentOrder();

                if(hasCurrentOrder) {
                    currentOrder = orderDAO.getCurrentOrder();


                } else {
                    Toast.makeText(v.getContext(), "There is not a current order", Toast.LENGTH_SHORT).show();
                }
            }
        };
    }

    @Override
    public void onBackPressed() {
        executeDone();
        super.onBackPressed();
    }

    private void executeDone() {
//        Intent resultIntent = new Intent();
//        resultIntent.putExtra("newCategory", CategoryEntryActivity.this.categoryTextEntry.getText().toString());
//        setResult(RESULT_OK, resultIntent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Menu is disabled for this
        return false;
    }
}
