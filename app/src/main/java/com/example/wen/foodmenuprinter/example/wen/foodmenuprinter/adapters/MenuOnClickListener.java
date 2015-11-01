package com.example.wen.foodmenuprinter.example.wen.foodmenuprinter.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.ViewGroup;

import com.wen.database.dao.MenuDAO;
import com.wen.database.model.Menu_Item;

import java.util.List;

/**
 * Created by Wen on 11/1/2015.
 */
public class MenuOnClickListener implements View.OnClickListener {

    private Context context;
    private ViewGroup parent;
    private MenuDAO menuDAO;
    private int position;
    private List<MenuObjectForListView> menuObjectsList;

    public MenuOnClickListener(Context context, ViewGroup parent, int position, List<MenuObjectForListView> menuObjectsList) {
        this.context = context;
        this.parent = parent;
        menuDAO = new MenuDAO(context);
        this.position = position;
        this.menuObjectsList = menuObjectsList;
    }

    @Override
    public void onClick(View v) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

        // set title
        alertDialogBuilder.setTitle("Delete Menu Item Dialog");


        // set dialog message
        alertDialogBuilder
                .setMessage("Click yes to exit!")
                .setCancelable(false)
                .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        menuDAO.deleteMenuItemById(menuObjectsList.get(position).getMenuItemId());
                        menuObjectsList.remove(position);
                    }
                })
                .setNegativeButton("No",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // if this button is clicked, just close
                        // the dialog box and do nothing
                        dialog.cancel();
                    }
                });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }
}
