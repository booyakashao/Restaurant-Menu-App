package com.example.wen.foodmenuprinter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.com.wen.utilities.ExpandableListAdapter;
import com.wen.database.dao.CategoryDAO;
import com.wen.database.dao.MenuDAO;
import com.wen.database.model.Menu_Item;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A fragment representing a single Category detail screen.
 * This fragment is either contained in a {@link CategoryListActivity}
 * in two-pane mode (on tablets) or a {@link CategoryDetailActivity}
 * on handsets.
 */
public class CategoryDetailFragment extends Fragment {
    protected MenuDAO menuDAO;
    protected CategoryDAO categoryDAO;
    protected List<Menu_Item> menu_items;

    //Expandable List Example================================================
    private ExpandableListAdapter listAdapter;
    private ExpandableListView expListView;
    private List<String> listMenuItemHeader;
    private HashMap<String, List<String>> listMenuItemSubView;
    //========================================================================

    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "category_id";

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public CategoryDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        menu_items = new ArrayList<Menu_Item>();
        menuDAO = new MenuDAO(getActivity());
        categoryDAO = new CategoryDAO(getActivity());

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.

            //Get Menu Items based on Category Id
            menu_items =  menuDAO.getMenuItemsByCategoryId(Integer.parseInt(getArguments().getString(ARG_ITEM_ID)));
        }
    }

    /*
     * Preparing the list data
     */
    private void prepareListData() {
        listMenuItemHeader = new ArrayList<String>();
        listMenuItemSubView = new HashMap<String, List<String>>();
        //DecimalFormat currencyFormat = new DecimalFormat("0.00");

        for(Menu_Item currentMenuItem : menu_items) {
            listMenuItemHeader.add(currentMenuItem.getName());
            List<String> detailList = new ArrayList<String>();
            detailList.add("Description: " + currentMenuItem.getDescription());
            detailList.add("Price: " + NumberFormat.getCurrencyInstance().format(currentMenuItem.getPrice()));
            detailList.add("Category: " + categoryDAO.getCategoryById(currentMenuItem.getCategory().getId()).getName());
            listMenuItemSubView.put(currentMenuItem.getName(),detailList);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_category_detail, container, false);

        LinearLayout fragmentLinearLayout = (LinearLayout) rootView.findViewById(R.id.fragment_category_detail_linear_layout);

        // get the listview
        expListView = (ExpandableListView) rootView.findViewById(R.id.exListView);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(getActivity(), listMenuItemHeader, listMenuItemSubView);

        // setting list adapter
        expListView.setAdapter(listAdapter);

        return rootView;
    }
}
