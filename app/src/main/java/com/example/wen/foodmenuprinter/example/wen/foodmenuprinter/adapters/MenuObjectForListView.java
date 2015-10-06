package com.example.wen.foodmenuprinter.example.wen.foodmenuprinter.adapters;

/**
 * Created by w.gu on 8/20/2015.
 */
public class MenuObjectForListView {
    private Integer menuItemId;
    private String menuItemName;
    private String menuItemDescription;
    private String menuPrice;

    public Integer getMenuItemId() { return menuItemId; }

    public void setMenuItemId(Integer menuItemId) { this.menuItemId = menuItemId; }

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

    public String getMenuPrice() { return menuPrice; }

    public void setMenuPrice(String menuPrice) { this.menuPrice = menuPrice; }
}
