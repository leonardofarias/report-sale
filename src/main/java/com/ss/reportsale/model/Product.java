package com.ss.reportsale.model;

import java.util.Objects;

public class Product {

  private Integer id;
  private Integer quantity;
  private Double price;

  public Product(Integer id, Integer quantity, Double price) {
    this.id = id;
    this.quantity = quantity;
    this.price = price;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public Double getTotalByProduct() {
    return this.quantity * this.price;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Product product = (Product) o;
    return Objects.equals(id, product.id)
        && Objects.equals(quantity, product.quantity)
        && Objects.equals(price, product.price);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, quantity, price);
  }

  @Override
  public String toString() {
    return "Product{" + "id=" + id + ", quantity=" + quantity + ", price=" + price + '}';
  }
}
