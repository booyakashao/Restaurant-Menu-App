package com.example.wen.foodmenuprinter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.wen.foodmenuprinter.example.wen.foodmenuprinter.adapters.MenuItemExpandableListAdapter;
import com.wen.database.model.Category;
import com.wen.database.model.Menu_Item;
import com.wen.database.model.OrderItems;
import com.wen.database.model.Orders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ViewCartActivity extends BaseActivityForCommon {

    ExpandableListView listOfOrdersByCategory;
    MenuItemExpandableListAdapter menuItemExpandableListAdapter;
    List<Category> listDataHeader;
    HashMap<Category, List<Menu_Item>> listDataChild;
    TextView subtotal;
    TextView total;
    Button printOrder;
    Orders currentOrder;
    List<String> orderText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_cart);

        // get the listview
        listOfOrdersByCategory  = (ExpandableListView) findViewById(R.id.expandableListView);

        //Set total amounts
        subtotal = (TextView) findViewById(R.id.viewCartMenuSubtotalAmount);
        total = (TextView) findViewById(R.id.viewCartMenuTotalAmount);

        //Set buttons
        printOrder = (Button) findViewById(R.id.viewCartPrintOrderButton);
        printOrder.setOnClickListener(printFinalOrder());

        // preparing list data
        prepareListData();

        menuItemExpandableListAdapter = new MenuItemExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        listOfOrdersByCategory.setAdapter(menuItemExpandableListAdapter);

    }

    /*
     * Preparing the list data
     */
    private void prepareListData() {
        Double subtotal_price_counter = new Double(0);
        listDataHeader = new ArrayList<Category>();
        listDataChild = new HashMap<Category, List<Menu_Item>>();

        currentOrder = ordersDAO.getCurrentOrder();

        List<OrderItems> listOfOrderItems = orderItemsDAO.getOrderItemByOrders(currentOrder.getId());

        for(OrderItems currentOrderItem : listOfOrderItems) {
            Menu_Item currentMenuItem = menuDAO.getMenuItemById(currentOrderItem.getMenu_instance().getId());
            currentMenuItem.setQuantity(currentOrderItem.getQuantity());

            Category currentCategory = categoryDAO.getCategoryById(currentMenuItem.getCategory().getId());

            if (listDataChild.containsKey(currentCategory)) {
                listDataChild.get(currentCategory).add(currentMenuItem);
            } else {
                listDataHeader.add(currentCategory);
                List<Menu_Item> newListOfMenuItems = new ArrayList<Menu_Item>();
                newListOfMenuItems.add(currentMenuItem);
                listDataChild.put(currentCategory, newListOfMenuItems);
            }

            subtotal_price_counter += currentMenuItem.getPrice() * currentMenuItem.getQuantity();
        }

        subtotal.setText(CommonUtils.convertDoubleToPrice(subtotal_price_counter));
        total.setText(CommonUtils.convertDoubleToPrice(subtotal_price_counter * (1 + (taxRateDAO.getTaxRate().getTaxRate() / 100.00))));
    }

    private void createOrderString() {
        orderText = new ArrayList<String>();

        orderText.add("Order Number: " + currentOrder.getId());
        orderText.add("==============================");
        orderText.add("");

        for (Map.Entry<Category, List<Menu_Item>> entry : listDataChild.entrySet()) {
            Category currentCategory = entry.getKey();

            orderText.add(currentCategory.getName());
            orderText.add("=================");

            List<Menu_Item> menuItems = entry.getValue();
            for(Menu_Item currentMenuItem : menuItems) {
                String leftSideOfString = currentMenuItem.getQuantity().toString() + " " + currentMenuItem.getName();
                Double totalPriceOfEntry = currentMenuItem.getQuantity() * currentMenuItem.getPrice();
                String rightSideOfString = CommonUtils.convertDoubleToPrice(totalPriceOfEntry);
                int lengthOfSpaces = 50 - (leftSideOfString.length() + rightSideOfString.length());
                for(int i = 0; i < lengthOfSpaces; i++) {
                    leftSideOfString = leftSideOfString.concat(" ");
                }

                orderText.add(leftSideOfString + rightSideOfString);
            }
            orderText.add(" ");
            orderText.add(" ");
        }
        orderText.add("==============================");
        orderText.add("Subtotal                     " + subtotal.getText().toString());
        orderText.add("Total                        " + total.getText().toString());
    }

    private View.OnClickListener printFinalOrder() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createOrderString();
                CommonUtils.doPrint((Activity) v.getContext(), orderText);
            }
        };

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        prepareListData();

        menuItemExpandableListAdapter = new MenuItemExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        listOfOrdersByCategory.setAdapter(menuItemExpandableListAdapter);
    }
}
