package com.ss.reportsale.model;

public class ReportData {

    public Integer qtdClient;
    public Integer qtdSalesmen;
    public Integer bestSaleId;
    public String worstSalesman;

    public ReportData(){}

    public ReportData(Integer qtdClient, Integer qtdSalesmen, Integer bestSaleId, String worstSalesman) {
        this.qtdClient = qtdClient;
        this.qtdSalesmen = qtdSalesmen;
        this.bestSaleId = bestSaleId;
        this.worstSalesman = worstSalesman;
    }

    public Integer getQtdClient() {
        return qtdClient;
    }

    public void setQtdClient(Integer qtdClient) {
        this.qtdClient = qtdClient;
    }

    public Integer getQtdSalesmen() {
        return qtdSalesmen;
    }

    public void setQtdSalesmen(Integer qtdSalesmen) {
        this.qtdSalesmen = qtdSalesmen;
    }

    public Integer getBestSaleId() {
        return bestSaleId;
    }

    public void setBestSaleId(Integer bestSaleId) {
        this.bestSaleId = bestSaleId;
    }

    public String getWorstSalesman() {
        return worstSalesman;
    }

    public void setWorstSalesman(String worstSalesman) {
        this.worstSalesman = worstSalesman;
    }
}
