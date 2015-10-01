package com.wen.database.model;

/**
 * Created by Wen on 7/27/2015.
 */
public class Menu_Item {
    private Integer id;
    private String name;
    private String description;
    private Double price;
    private Category category;
    private Integer quantity; // Used only for cart

    public Menu_Item() {
    }

    public Menu_Item(Integer id, String name, String description, Double price, Category category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Integer getQuantity() { return this.quantity; }

    public void setQuantity(Integer quantity) { this.quantity = quantity; }
}
