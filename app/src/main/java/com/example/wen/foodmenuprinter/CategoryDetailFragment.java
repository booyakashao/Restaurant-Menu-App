package com.example.wen.foodmenuprinter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wen.foodmenuprinter.example.wen.foodmenuprinter.adapters.MenuListAdapter;
import com.example.wen.foodmenuprinter.example.wen.foodmenuprinter.adapters.MenuObjectForListView;
import com.example.wen.foodmenuprinter.openMenuModals.AddItemToCart;
import com.wen.database.dao.MenuDAO;
import com.wen.database.model.Menu_Item;

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
    MenuDAO menuDAO;
    List<Menu_Item> menu_items;

    MenuListAdapter menuItemListAdapter;
    Button checkoutButton;

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



        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.

            //Get Menu Items based on Category Id
            menu_items =  menuDAO.getMenuItemsByCategoryId(Integer.parseInt(getArguments().getString(ARG_ITEM_ID)));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_category_detail, container, false);

        RelativeLayout fragmentLinearLayout = (RelativeLayout) rootView.findViewById(R.id.fragment_category_detail_linear_layout);
        checkoutButton = (Button) rootView.findViewById(R.id.checkout_button);


        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        List<MenuObjectForListView> menuObjectForListView = getDataForListView();

        menuItemListAdapter = new MenuListAdapter(menuObjectForListView);

        ListView menuItemListView = (ListView) rootView.findViewById(R.id.menuListItem);
        menuItemListView.setAdapter(menuItemListAdapter);
        menuItemListView.setOnItemClickListener(addToCartOnClickListener());

        checkoutButton.setOnClickListener(openCartOnClickListener());

        return rootView;
    }

    public List<MenuObjectForListView> getDataForListView()
    {
        List<MenuObjectForListView> MenuObjectsList = new ArrayList<MenuObjectForListView>();

        for(Menu_Item currentMenuItem : menu_items) {
            MenuObjectForListView newMenuObjectForListView = new MenuObjectForListView();
            newMenuObjectForListView.setMenuItemId(currentMenuItem.getId());
            newMenuObjectForListView.setMenuItemName(currentMenuItem.getName());
            newMenuObjectForListView.setMenuItemDescription(currentMenuItem.getDescription());
            newMenuObjectForListView.setMenuPrice(CommonUtils.convertDoubleToPrice(currentMenuItem.getPrice()));
            MenuObjectsList.add(newMenuObjectForListView);
        }

        return MenuObjectsList;

    }

    private AdapterView.OnItemClickListener addToCartOnClickListener() {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MenuObjectForListView chapter = menuItemListAdapter.getMenuItem(position);

                Intent addItemToCartIntent = new Intent(parent.getContext(), AddItemToCart.class);
                addItemToCartIntent.putExtra("menu_item_id", chapter.getMenuItemId());

                startActivity(addItemToCartIntent);
            }
        };
    }

    private View.OnClickListener openCartOnClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewCartActivity = new Intent(v.getContext(), ViewCartActivity.class);
                startActivity(viewCartActivity);
            }
        };
    }
}
