package com.ss.reportsale.repository;

import com.ss.reportsale.model.Client;
import com.ss.reportsale.model.Product;
import com.ss.reportsale.model.Sale;
import com.ss.reportsale.model.Salesman;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class InputRepository {

  private final Logger log = LoggerFactory.getLogger(InputRepository.class);
  private List<Salesman> salesmen;
  private List<Client> clients;
  private List<Sale> sales;

  public InputRepository() {
    this.salesmen = new ArrayList<>();
    this.clients = new ArrayList<>();
    this.sales = new ArrayList<>();
  }

  public void create(String[] partes) {
    switch (partes[0]) {
      case "001":
        createSalesman(partes);
        break;
      case "002":
        createClient(partes);
        break;
      case "003":
        createSale(partes);
        break;
      default:
        break;
    }
  }

  public void createSalesman(String[] partes) {
    try {
      salesmen.add(new Salesman(partes[1], partes[2], Double.parseDouble(partes[3])));
    } catch (Exception e) {
      log.info(e.getMessage());
    }
  }

  public void createClient(String[] partes) {
    try {
      clients.add(new Client(partes[1], partes[2], partes[3]));
    } catch (Exception e) {
      log.info(e.getMessage());
    }
  }

  public void createSale(String[] partes) {
    try {

      List<Product> products = new ArrayList<>();

      List<String> strings = Arrays.asList(partes[2].replace("[", "").replace("]", "").split(","));

      for (String item : strings) {
        String[] partesItem = item.split("-");
        products.add(
            new Product(
                Integer.parseInt(partesItem[0]),
                Integer.parseInt(partesItem[1]),
                Double.parseDouble(partesItem[2])));
      }

      sales.add(new Sale(Integer.parseInt(partes[1]), products, partes[3]));
    } catch (Exception e) {
      log.info(e.getMessage());
    }
  }

  public List<Salesman> getSalesmen() {
    return salesmen;
  }

  public void setSalesmen(List<Salesman> salesmen) {
    this.salesmen = salesmen;
  }

  public List<Client> getClients() {
    return clients;
  }

  public void setClients(List<Client> clients) {
    this.clients = clients;
  }

  public List<Sale> getSales() {
    return sales;
  }

  public void setSales(List<Sale> sales) {
    this.sales = sales;
  }
}
