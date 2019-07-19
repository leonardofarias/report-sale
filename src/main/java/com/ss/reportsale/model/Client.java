package com.ss.reportsale.model;

public class Client extends Person {

  private String cnpj;
  private String businessArea;

  public Client(String name, String cnpj, String businessArea) {
    super(name);
    this.cnpj = cnpj;
    this.businessArea = businessArea;
  }

  public String getCnpj() {
    return cnpj;
  }

  public void setCnpj(String cnpj) {
    this.cnpj = cnpj;
  }

  public String getBusinessArea() {
    return businessArea;
  }

  public void setBusinessArea(String businessArea) {
    this.businessArea = businessArea;
  }
}
