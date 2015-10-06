package com.example.wen.foodmenuprinter;

import android.os.Bundle;
import android.widget.ExpandableListView;

import com.example.wen.foodmenuprinter.example.wen.foodmenuprinter.adapters.MenuItemExpandableListAdapter;
import com.wen.database.model.Category;
import com.wen.database.model.Menu_Item;
import com.wen.database.model.OrderItems;
import com.wen.database.model.Orders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ViewCartActivity extends BaseActivityForCommon {

    ExpandableListView listOfOrdersByCategory;
    MenuItemExpandableListAdapter menuItemExpandableListAdapter;
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

        menuItemExpandableListAdapter = new MenuItemExpandableListAdapter(this, listDataHeader, listDataChild);

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
