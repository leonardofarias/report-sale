package com.ss.reportsale.service;

import com.ss.reportsale.exception.BusinessException;
import com.ss.reportsale.model.Client;
import com.ss.reportsale.model.Product;
import com.ss.reportsale.model.Sale;
import com.ss.reportsale.model.Salesman;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Repository
public class DataService {

  private List<Salesman> salesmen;
  private List<Client> clients;
  private List<Sale> sales;

  public DataService() {
    this.salesmen = new ArrayList<>();
    this.clients = new ArrayList<>();
    this.sales = new ArrayList<>();
  }

  public void create(String[] partes) throws BusinessException {
    switch (partes[0]) {
      case "001":
        this.salesmen.add(createSalesman(partes));
        break;
      case "002":
        this.clients.add(createClient(partes));
        break;
      case "003":
        this.sales.add(createSale(partes));
        break;
      default:
        break;
    }
  }

  public Salesman createSalesman(String[] partes) throws BusinessException {

    if (Objects.isNull(partes[1]) || partes[1].equals("")) {
      throw new BusinessException("CPF inválido");
    }

    if (Objects.isNull(partes[2]) || partes[2].equals("")) {
      throw new BusinessException("Nome inválido");
    }

    if (Objects.isNull(partes[3])
        || partes[3].equals("")
        || Double.isNaN(Double.parseDouble(partes[3]))) {
      throw new BusinessException("Salário inválido");
    }

    return new Salesman(partes[1], partes[2], Double.parseDouble(partes[3]));
  }

  public Client createClient(String[] partes) throws BusinessException {

    if (Objects.isNull(partes[1]) || partes[1].equals("")) {
      throw new BusinessException("CNPJ inválido");
    }

    if (Objects.isNull(partes[2]) || partes[2].equals("")) {
      throw new BusinessException("Nome inválido");
    }

    if (Objects.isNull(partes[3]) || partes[3].equals("")) {
      throw new BusinessException("Área de Negócio inválida");
    }

    return new Client(partes[1], partes[2], partes[3]);
  }

  public Sale createSale(String[] partes) throws BusinessException {

    List<Product> products = new ArrayList<>();
    List<String> strings = Arrays.asList(partes[2].replaceAll("[\\[\\]]", "").split(","));

    if (strings.isEmpty()) {
      throw new BusinessException("Dados inválidos");
    }

    if (Objects.isNull(partes[1])
        || partes[1].equals("")
        || Double.isNaN(Double.parseDouble(partes[1]))) {
      throw new BusinessException("Id inválido");
    }

    for (String item : strings) {
      String[] partesItem = item.split("-");

      if (Objects.isNull(partesItem[0])
          || partesItem[0].equals("")
          || Double.isNaN(Double.parseDouble(partesItem[0]))) {
        throw new BusinessException("Id inválido");
      }

      if (Objects.isNull(partesItem[1])
          || partes[1].equals("")
          || Double.isNaN(Double.parseDouble(partesItem[1]))) {
        throw new BusinessException("Quantidade inválida");
      }

      if (Objects.isNull(partesItem[2])
          || partes[2].equals("")
          || Double.isNaN(Double.parseDouble(partesItem[2]))) {
        throw new BusinessException("Preço inválido");
      }

      products.add(
          new Product(
              Integer.parseInt(partesItem[0]),
              Integer.parseInt(partesItem[1]),
              Double.parseDouble(partesItem[2])));
    }

    if (Objects.isNull(partes[3]) || partes[3].equals("")) {
      throw new BusinessException("Nome do Vendedor inválido");
    }

    return new Sale(Integer.parseInt(partes[1]), products, partes[3]);
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
