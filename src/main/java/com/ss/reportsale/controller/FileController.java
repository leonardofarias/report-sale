package com.ss.reportsale.controller;

import com.ss.reportsale.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
public class FileController {

  private final Logger log = LoggerFactory.getLogger(FileController.class);
  private final String path = System.getProperty("user.home") + "\\data\\in";
  private final String splitVar = "ç";
  private List<Salesman> salesmen;
  private List<Client> clients;
  private List<Sale> sales;

  public FileController() {
    this.salesmen = new ArrayList<>();
    this.clients = new ArrayList<>();
    this.sales = new ArrayList<>();
    start();
  }


  public void start() {

    // buscar arquivos
    List<File> list = listFiles();

    //ler arquivos
    for (File file : list) {
      readFile(file);
    }
  }

  public List<File> listFiles() {

    log.info("Diretório: " + path);

    List<File> result = null;

    try (Stream<Path> walk = Files.list(Paths.get(path))) {

      result =
          walk.map(x -> x.toFile())
              .filter(f -> f.getName().endsWith(".dat"))
              .collect(Collectors.toList());

      result.forEach(item -> log.info("Arquivo: " + item.getName()));

    } catch (IOException e) {
      e.printStackTrace();
    }
    return result;
  }

  private void readFile(File file) {

    try (BufferedReader br =
        new BufferedReader(
            new InputStreamReader(new FileInputStream(file), Charset.forName("ISO-8859-1")))) {
      String strLine;

      while ((strLine = br.readLine()) != null) {

        String[] partes = strLine.split(splitVar);

        createEntities(file.getName(), partes);

      }

      generateOutput(salesmen, clients, sales);

    } catch (FileNotFoundException ex) {
      ex.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void createEntities(String fileName, String[] partes) {


    switch (partes[0]) {
      case "001":
        salesmen.add(new Salesman(partes[1], partes[2], Double.parseDouble(partes[3])));
        break;
      case "002":

        clients.add(new Client(partes[1], partes[2], partes[3]));
        break;

      case "003":

        List<Product> products = new ArrayList<>();

        List<String> strings =
            Arrays.asList(partes[2].replace("[", "").replace("]", "").split(","));

        for (String item : strings) {
          String[] partesItem = item.split("-");
          products.add(
              new Product(
                  Integer.parseInt(partesItem[0]),
                  Integer.parseInt(partesItem[1]),
                  Double.parseDouble(partesItem[2])));
        }

        sales.add(new Sale(Integer.parseInt(partes[1]), products, partes[3]));
        break;

      default:
        break;
    }

  }

  private void generateOutput(List<Salesman> salesmen, List<Client> clients, List<Sale> sales) {

    ReportData reportData = new ReportData();

    reportData.setQtdClient(clients.size());
    reportData.setQtdSalesmen(salesmen.size());
    reportData.setBestSaleId(sales.stream().max(Comparator.comparingDouble(Sale::getTotalSale)).get().getId());

    Map<String, Double> result =
            sales.stream().collect(Collectors.groupingBy(sale -> sale.getSalesmanName(), Collectors.summingDouble(Sale::getTotalSale)));

    Map.Entry<String, Double> min = Collections.min(result.entrySet(),
            Comparator.comparing(Map.Entry::getValue));

    reportData.setWorstSalesman(min.getKey());

    this.salesmen = new ArrayList<>();
    this.clients = new ArrayList<>();
    this.sales = new ArrayList<>();

  }
}
