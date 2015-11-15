package com.example.wen.foodmenuprinter.example.wen.foodmenuprinter.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.wen.foodmenuprinter.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by w.gu on 8/20/2015.
 */
public class MenuListAdapter extends BaseAdapter {

    private List<MenuObjectForListView> menuObjectsList = new ArrayList<MenuObjectForListView>();

    public MenuListAdapter(List<MenuObjectForListView> targetMenuObjectsList) {
        menuObjectsList = targetMenuObjectsList;
    }

    @Override
    public int getCount() {
        return menuObjectsList.size();
    }

    @Override
    public MenuObjectForListView getItem(int position) {
        return menuObjectsList.get(position);
    }

    public MenuObjectForListView getMenuItem(int position)
    {
        return menuObjectsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView==null) {
            LayoutInflater inflater = (LayoutInflater) LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.menu_list_item, parent, false);

            MenuObjectForListView targetMenuObjectListItem = getItem(position);

            TextView menuItemName = (TextView)convertView.findViewById(R.id.menu_title_text_view);
            TextView menuItemDesc = (TextView)convertView.findViewById(R.id.menu_description_text_view);
            TextView menuItemPrice = (TextView) convertView.findViewById(R.id.menu_price_text_view);
            menuItemName.setText(targetMenuObjectListItem.getMenuItemName());
            menuItemDesc.setText(targetMenuObjectListItem.getMenuItemDescription());
            menuItemPrice.setText(String.valueOf(targetMenuObjectListItem.getMenuPrice()));

            Button addToCartButton = (Button) convertView.findViewById(R.id.menu_item_add_to_cart_button);
            addToCartButton.setOnClickListener(new MenuOnClickAddToCartListener(convertView.getContext(), parent, position, menuObjectsList));

            Button editButton = (Button) convertView.findViewById(R.id.menu_item_edit_button);
            editButton.setOnClickListener(new MenuOnClickEditMenuItemListener(convertView.getContext(), parent, position, menuObjectsList));

            Button deleteButton = (Button) convertView.findViewById(R.id.menu_item_delete_button);
            deleteButton.setOnClickListener(new MenuOnClickDeleteListener(convertView.getContext(), parent, position, menuObjectsList));
        }

        return convertView;
    }



}
