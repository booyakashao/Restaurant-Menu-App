package com.example.wen.foodmenuprinter.example.wen.foodmenuprinter.adapters;

/**
 * Created by w.gu on 8/20/2015.
 */
public class MenuObjectForListView {
    private String menuItemName;
    private String menuItemDescription;
    private Double menuPrice;

    public String getMenuItemName() {
        return menuItemName;
    }

    public void setMenuItemName(String menuItemName) {
        this.menuItemName = menuItemName;
    }

    public String getMenuItemDescription() {
        return menuItemDescription;
    }

    public void setMenuItemDescription(String menuItemDescription) { this.menuItemDescription = menuItemDescription; }

    public Double getMenuPrice() { return menuPrice; }

    public void setMenuPrice(Double menuPrice) { this.menuPrice = menuPrice; }
}
