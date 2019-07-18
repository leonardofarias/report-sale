package com.ss.reportsale.model;

public class SaleSum {

    private Integer id;
    private Double totalSale;

    public SaleSum(Integer id, Double totalSale) {
        this.id = id;
        this.totalSale = totalSale;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getTotalSale() {
        return totalSale;
    }

    public void setTotalSale(Double totalSale) {
        this.totalSale = totalSale;
    }
}
