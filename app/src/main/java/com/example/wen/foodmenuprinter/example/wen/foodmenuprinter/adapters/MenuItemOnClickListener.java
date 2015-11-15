package com.example.wen.foodmenuprinter.example.wen.foodmenuprinter.adapters;

import android.view.View;
import android.view.ViewGroup;
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
public class MenuItemOnClickListener implements View.OnClickListener {

    private Menu_Item menuItem;
    private OrderItemsDAO orderItemsDAO;
    private OrdersDAO ordersDAO;
    private View view;

    public MenuItemOnClickListener(Menu_Item menuItem, OrderItemsDAO orderItemsDAO, OrdersDAO ordersDAO, View parent) {
        this.menuItem = menuItem;
        this.orderItemsDAO = orderItemsDAO;
        this.ordersDAO = ordersDAO;
        this.view = parent;
    }

    @Override
    public void onClick(View v) {
        Orders currentOrder = ordersDAO.getCurrentOrder();
        OrderItems currentOrderItem = orderItemsDAO.getExistingOrderItem(currentOrder.getId(), menuItem.getId());
        Integer resultingInteger = orderItemsDAO.deleteOrderItem(currentOrderItem.getId());

        ViewCartActivity viewCartActivity = (ViewCartActivity) v.getContext();
        viewCartActivity.onActivityResult(1, 1, null);
    }
}
