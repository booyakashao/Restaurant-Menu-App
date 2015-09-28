package com.example.wen.foodmenuprinter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;

import com.example.wen.foodmenuprinter.example.wen.foodmenuprinter.adapters.MenuItemExpandableListAdapter;
import com.wen.database.model.Category;
import com.wen.database.model.Menu_Item;
import com.wen.database.model.OrderItems;
import com.wen.database.model.Orders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ViewCartActivity extends BaseActivityForCommon {

    ExpandableListView listOfOrdersByCategory;
    MenuItemExpandableListAdapter menuItemExpandableListAdapter;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_cart);

        // get the listview
        listOfOrdersByCategory  = (ExpandableListView) findViewById(R.id.expandableListView);

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
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        Orders currentOrder = ordersDAO.getCurrentOrder();

        List<OrderItems> listOfOrderItems = orderItemsDAO.getOrderItemByOrders(currentOrder.getId());

        for(OrderItems currentOrderItem : listOfOrderItems) {
            Menu_Item currentMenuItem = menuDAO.getMenuItemById(currentOrderItem.getMenu_instance().getId());

            Category currentCategory = categoryDAO.getCategoryById(currentMenuItem.getCategory().getId());

            if (listDataChild.containsKey(currentCategory.getName())) {
                listDataChild.get(currentCategory.getName()).add(currentMenuItem.getName());
            } else {
                listDataHeader.add(currentCategory.getName());
                List<String> newListOfMenuItems = new ArrayList<String>();
                newListOfMenuItems.add(currentMenuItem.getName());
                listDataChild.put(currentCategory.getName(), newListOfMenuItems);
            }
        }
    }
}
