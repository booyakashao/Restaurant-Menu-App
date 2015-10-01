package com.example.wen.foodmenuprinter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;

import com.example.wen.foodmenuprinter.example.wen.foodmenuprinter.adapters.MenuItemExpandableListAdapter;
import com.example.wen.foodmenuprinter.example.wen.foodmenuprinter.adapters.MenuItemExpandableListAdapter2;
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
    MenuItemExpandableListAdapter2 menuItemExpandableListAdapter;
//    List<String> listDataHeader;
//    HashMap<String, List<String>> listDataChild;
    List<Category> listDataHeader;
    HashMap<Category, List<Menu_Item>> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_cart);

        // get the listview
        listOfOrdersByCategory  = (ExpandableListView) findViewById(R.id.expandableListView);

        // preparing list data
        prepareListData();

        menuItemExpandableListAdapter = new MenuItemExpandableListAdapter2(this, listDataHeader, listDataChild);

        // setting list adapter
        listOfOrdersByCategory.setAdapter(menuItemExpandableListAdapter);
    }

    /*
     * Preparing the list data
     */
    private void prepareListData() {
        listDataHeader = new ArrayList<Category>();
        listDataChild = new HashMap<Category, List<Menu_Item>>();

        Orders currentOrder = ordersDAO.getCurrentOrder();

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
        }
    }
}
