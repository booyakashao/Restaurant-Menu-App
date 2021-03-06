package com.example.wen.foodmenuprinter.example.wen.foodmenuprinter.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.wen.foodmenuprinter.CommonUtils;
import com.example.wen.foodmenuprinter.R;
import com.wen.database.dao.OrderItemsDAO;
import com.wen.database.dao.OrdersDAO;
import com.wen.database.model.Category;
import com.wen.database.model.Menu_Item;
import com.wen.database.model.Orders;

import java.util.HashMap;
import java.util.List;

/**
 * Created by w.gu on 9/28/2015.
 */
public class MenuItemExpandableListAdapter extends BaseExpandableListAdapter {

    private Context _context;

    private List<Category> listOfCategories; // header titles
    // child data in format header title, child title
    private HashMap<Category, List<Menu_Item>> listOfChildMenuItems;

    private OrderItemsDAO orderItemsDAO;
    private OrdersDAO ordersDAO;


    public MenuItemExpandableListAdapter(Context context, List<Category> listDataHeader, HashMap<Category, List<Menu_Item>> listChildData) {
        this._context = context;
        this.listOfCategories = listDataHeader;
        this.listOfChildMenuItems = listChildData;
        orderItemsDAO = new OrderItemsDAO(context);
        ordersDAO = new OrdersDAO(context);
    }

    @Override
    public int getGroupCount() {
        return this.listOfCategories.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.listOfChildMenuItems.get(this.listOfCategories.get(groupPosition))
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.listOfCategories.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this.listOfChildMenuItems.get(this.listOfCategories.get(groupPosition))
                .get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        Category category = (Category) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.activity_view_cart_expandable_list_group, null);
        }

        TextView viewCartMenuItemLabel = (TextView) convertView
                .findViewById(R.id.viewCartMenuItemLabel);
        viewCartMenuItemLabel.setTypeface(null, Typeface.BOLD);
        viewCartMenuItemLabel.setText(category.getName());

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final Menu_Item menuItem = (Menu_Item) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.activity_view_cart_expandable_list_item, null);
        }

        TextView viewCartMenuItemDetailName = (TextView) convertView
                .findViewById(R.id.viewCartMenuItemDetailName);
        TextView viewCartMenuItemDetailDescription = (TextView) convertView
                .findViewById(R.id.viewCartMenuItemDetailDescription);
        TextView viewCartMenuItemDetailPrice = (TextView) convertView
                .findViewById(R.id.viewCartMenuItemDetailPrice);

        Spinner quantitySpinner = (Spinner) convertView.findViewById(R.id.viewCartMenuItemDetailQuantitySpinner);

        Button deleteItemButton = (Button) convertView.findViewById(R.id.viewCartRemoveItemButton);
        deleteItemButton.setOnClickListener(new MenuItemOnClickListener(menuItem, orderItemsDAO, ordersDAO, convertView));

        viewCartMenuItemDetailName.setText(menuItem.getName());
        viewCartMenuItemDetailDescription.setText(menuItem.getDescription());
        viewCartMenuItemDetailPrice.setText(CommonUtils.convertDoubleToPrice(menuItem.getPrice()));

        ArrayAdapter<CharSequence> quantityAdapter = ArrayAdapter.createFromResource(
                _context, R.array.quantity_spinner, android.R.layout.simple_spinner_item);

        quantityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        quantitySpinner.setAdapter(quantityAdapter);
        int position = menuItem.getQuantity() - 1;
        quantitySpinner.setSelection(position);
        quantitySpinner.setOnItemSelectedListener(new MenuItemOnSelectedListener(menuItem, orderItemsDAO, ordersDAO));

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
