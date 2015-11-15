package com.example.wen.foodmenuprinter.example.wen.foodmenuprinter.adapters;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.wen.foodmenuprinter.ViewCartActivity;
import com.wen.database.dao.OrderItemsDAO;
import com.wen.database.dao.OrdersDAO;
import com.wen.database.model.Menu_Item;
import com.wen.database.model.OrderItems;
import com.wen.database.model.Orders;

/**
 * Created by Wen on 10/10/2015.
 */
public class MenuItemOnSelectedListener implements AdapterView.OnItemSelectedListener {

    private Menu_Item menuItem;
    private OrderItemsDAO orderItemsDAO;
    private OrdersDAO ordersDAO;

    public MenuItemOnSelectedListener(Menu_Item menuItem, OrderItemsDAO orderItemsDAO, OrdersDAO ordersDAO) {
        this.menuItem = menuItem;
        this.orderItemsDAO = orderItemsDAO;
        this.ordersDAO = ordersDAO;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Orders currentOrder = ordersDAO.getCurrentOrder();
        OrderItems currentOrderItem = orderItemsDAO.getExistingOrderItem(currentOrder.getId(), menuItem.getId());
        orderItemsDAO.createNewOrderItem(currentOrder.getId(), menuItem.getId(), (position + 1));

        if(currentOrderItem.getQuantity() != (position + 1)) {
            ViewCartActivity viewCartActivity = (ViewCartActivity) view.getContext();
            viewCartActivity.onActivityResult(1, 1, null);
        }
    }



    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
