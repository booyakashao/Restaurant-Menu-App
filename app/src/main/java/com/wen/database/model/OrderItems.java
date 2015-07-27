package com.wen.database.model;

import android.view.Menu;

/**
 * Created by Wen on 7/27/2015.
 */
public class OrderItems {
    private Long id;
    private Orders order_instance;
    private Menu_Item menu_instance;
    private Integer quantity;

    public OrderItems() {

    }

    public OrderItems(Long id, Orders order_instance, Menu_Item menu_instance, Integer quantity) {
        this.id = id;
        this.order_instance = order_instance;
        this.menu_instance = menu_instance;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Orders getOrder_instance() {
        return order_instance;
    }

    public void setOrder_instance(Orders order_instance) {
        this.order_instance = order_instance;
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
