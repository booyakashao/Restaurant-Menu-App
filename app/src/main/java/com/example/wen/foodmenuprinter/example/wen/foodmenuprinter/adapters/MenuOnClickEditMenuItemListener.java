package com.example.wen.foodmenuprinter.example.wen.foodmenuprinter.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import com.example.wen.foodmenuprinter.openMenuModals.EditItem;

import java.util.List;

/**
 * Created by Wen on 11/15/2015.
 */
public class MenuOnClickEditMenuItemListener implements View.OnClickListener {

    private Context context;
    private ViewGroup parent;
    private int position;
    private List<MenuObjectForListView> menuObjectsList;

    public MenuOnClickEditMenuItemListener(Context context, ViewGroup parent, int position, List<MenuObjectForListView> menuObjectsList) {
        this.context = context;
        this.parent = parent;
        this.position = position;
        this.menuObjectsList = menuObjectsList;
    }



    @Override
    public void onClick(View v) {
        MenuObjectForListView currentMenuItem = menuObjectsList.get(position);

        Intent editItemIntent = new Intent(context, EditItem.class);
        editItemIntent.putExtra("menu_item_id", currentMenuItem.getMenuItemId());

        int result = 1;

        FragmentActivity targetActivity = (FragmentActivity) context;
        targetActivity.startActivityForResult(editItemIntent, result);
    }
}
