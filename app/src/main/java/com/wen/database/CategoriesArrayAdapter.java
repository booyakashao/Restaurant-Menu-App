package com.wen.database;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by w.gu on 8/4/2015.
 */
public class CategoriesArrayAdapter<T> extends ArrayAdapter<T> {

    public CategoriesArrayAdapter(Context context, List<T> items) {
        super(context, android.R.layout.simple_list_item_activated_1, android.R.id.text1, items);
    }

    public CategoriesArrayAdapter(Context context, T[] items) {
        super(context, android.R.layout.simple_list_item_activated_1, android.R.id.text1, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = super.getView(position, convertView, parent);
        TextView textView = (TextView) view.findViewById(android.R.id.text1);
        textView.setTypeface(null, Typeface.NORMAL);
        //textView.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "font/segoeuil.tff"));

        return view;
    }
}
