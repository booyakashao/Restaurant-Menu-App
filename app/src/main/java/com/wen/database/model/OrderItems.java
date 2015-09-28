package com.wen.database.model;

import android.view.Menu;

/**
 * Created by Wen on 7/27/2015.
 */
public class OrderItems {
    private Integer id;
    private Orders order_item;
    private Menu_Item menu_instance;
    private Integer quantity;

    public OrderItems() {

    }

    public OrderItems(Integer id, Orders order_instance, Menu_Item menu_instance, Integer quantity) {
        this.id = id;
        this.order_item = order_instance;
        this.menu_instance = menu_instance;
        this.quantity = quantity;
    }

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public Orders getOrder_item() {
        return order_item;
    }

    public void setOrder_item(Orders order_item) {
        this.order_item = order_item;
    }

    public Menu_Item getMenu_instance() {
        return menu_instance;
    }

    public void setMenu_instance(Menu_Item menu_instance) {
        this.menu_instance = menu_instance;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
