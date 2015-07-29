package com.wen.database.model;

/**
 * Created by Wen on 7/27/2015.
 */
public class TaxRate {
    private Long id;
    private Double taxRate;

    public TaxRate() {
    }

    public TaxRate(Long id, Double taxRate) {
        this.id = id;
        this.taxRate = taxRate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(Double taxRate) {
        this.taxRate = taxRate;
    }
}
