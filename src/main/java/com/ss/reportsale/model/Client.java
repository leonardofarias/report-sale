package com.ss.reportsale.model;

import java.util.Objects;

public class Client {

    private String cnpj;
    private String name;
    private String businessArea;

    public Client(String cnpj, String name, String businessArea) {
        this.cnpj = cnpj;
        this.name = name;
        this.businessArea = businessArea;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBusinessArea() {
        return businessArea;
    }

    public void setBusinessArea(String businessArea) {
        this.businessArea = businessArea;
    }

    @Override
    public String toString() {
        return "Client{" +
                "cnpj='" + cnpj + '\'' +
                ", name='" + name + '\'' +
                ", businessArea='" + businessArea + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(cnpj, client.cnpj) &&
                Objects.equals(name, client.name) &&
                Objects.equals(businessArea, client.businessArea);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cnpj, name, businessArea);
    }
}
