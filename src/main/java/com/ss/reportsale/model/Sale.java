package com.ss.reportsale.model;

import java.util.List;
import java.util.Objects;

public class Sale {

  private Integer id;
  private List<Product> products;
  private String salesmanName;

  public Sale(Integer id, List<Product> products, String salesmanName) {
    this.id = id;
    this.products = products;
    this.salesmanName = salesmanName;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public List<Product> getProducts() {
    return products;
  }

  public void setProducts(List<Product> products) {
    this.products = products;
  }

  public String getSalesmanName() {
    return salesmanName;
  }

  public void setSalesmanName(String salesmanName) {
    this.salesmanName = salesmanName;
  }

  public Double getTotalSale() {
    return products.stream().mapToDouble(Product::getTotalByProduct).sum();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Sale sale = (Sale) o;
    return Objects.equals(id, sale.id)
        && Objects.equals(products, sale.products)
        && Objects.equals(salesmanName, sale.salesmanName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, products, salesmanName);
  }

  @Override
  public String toString() {
    return "Sale{"
        + "id="
        + id
        + ", products="
        + products
        + ", salesmanName='"
        + salesmanName
        + '\''
        + '}';
  }
}
