package com.example.wen.foodmenuprinter.example.wen.foodmenuprinter.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;

import com.example.wen.foodmenuprinter.openMenuModals.AddItemToCart;
import com.wen.database.dao.MenuDAO;

import java.util.List;

/**
 * Created by Wen on 11/14/2015.
 */
public class MenuOnClickAddToCartListener implements View.OnClickListener {

    private Context context;
    private ViewGroup parent;
    private int position;
    private List<MenuObjectForListView> menuObjectsList;

    public MenuOnClickAddToCartListener(Context context, ViewGroup parent, int position, List<MenuObjectForListView> menuObjectsList) {
        this.context = context;
        this.parent = parent;
        this.position = position;
        this.menuObjectsList = menuObjectsList;
    }

    @Override
    public void onClick(View v) {
        MenuObjectForListView currentMenuItem = menuObjectsList.get(position);

        Intent addItemToCartIntent = new Intent(context, AddItemToCart.class);
        addItemToCartIntent.putExtra("menu_item_id", currentMenuItem.getMenuItemId());

        context.startActivity(addItemToCartIntent);
    }
}
