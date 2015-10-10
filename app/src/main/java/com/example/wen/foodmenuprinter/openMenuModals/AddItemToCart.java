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
import com.wen.database.dao.OrderItemsDAO;
import com.wen.database.dao.OrdersDAO;
import com.wen.database.model.Orders;

public class AddItemToCart extends Activity {

    MenuDAO menuDAO;
    OrdersDAO orderDAO;
    OrderItemsDAO orderItemsDAO;

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
        orderItemsDAO = new OrderItemsDAO(this);

        addToCartButton = (Button) findViewById(R.id.addToCartButton);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND,
                WindowManager.LayoutParams.FLAG_DIM_BEHIND);

        quantityNumberPicker = (NumberPicker) findViewById(R.id.quantityNumberPicker);

        quantityNumberPicker.setMinValue(1);
        quantityNumberPicker.setMaxValue(459);
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

                boolean hasCurrentOrder = orderDAO.hasCurrentOrder();

                if(hasCurrentOrder) {
                    currentOrder = orderDAO.getCurrentOrder();
                    Integer currentSelectedQuantity = quantityNumberPicker.getValue();
                    if(orderItemsDAO.createNewOrderItem(currentOrder.getId(), menu_item_id, currentSelectedQuantity)) {
                        executeDone();
                    } else {
                        Toast.makeText(v.getContext(), "Order was not completed", Toast.LENGTH_SHORT).show();
                    }
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
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Menu is disabled for this
        return false;
    }
}
