package com.ss.reportsale.model;

import java.util.Objects;

public class ReportData {

  public Integer qtdClient;
  public Integer qtdSalesmen;
  public Integer bestSaleId;
  public String worstSalesman;

  public ReportData() {}

  public ReportData(
      Integer qtdClient, Integer qtdSalesmen, Integer bestSaleId, String worstSalesman) {
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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ReportData that = (ReportData) o;
    return Objects.equals(qtdClient, that.qtdClient) &&
            Objects.equals(qtdSalesmen, that.qtdSalesmen) &&
            Objects.equals(bestSaleId, that.bestSaleId) &&
            Objects.equals(worstSalesman, that.worstSalesman);
  }

  @Override
  public int hashCode() {
    return Objects.hash(qtdClient, qtdSalesmen, bestSaleId, worstSalesman);
  }
}
