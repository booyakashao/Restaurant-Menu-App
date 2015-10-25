package com.wen.database.model;

/**
 * Created by Wen on 7/27/2015.
 */
public class TaxRate {
    private Integer id;
    private Double taxRate;

    public TaxRate() {
    }

    public TaxRate(Integer id, Double taxRate) {
        this.id = id;
        this.taxRate = taxRate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(Double taxRate) {
        this.taxRate = taxRate;
    }
}
