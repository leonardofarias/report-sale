package com.ss.reportsale.service;

import com.ss.reportsale.exception.BusinessException;
import com.ss.reportsale.model.Client;
import com.ss.reportsale.model.ReportData;
import com.ss.reportsale.model.Sale;
import com.ss.reportsale.model.Salesman;
import com.ss.reportsale.utils.Parameters;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReportService {

  private final DataService dataService;

  public ReportService(DataService dataService) {
    this.dataService = dataService;
  }

  public ReportData generateOutput(
      List<Salesman> salesmen, List<Client> clients, List<Sale> sales) {

    ReportData reportData = new ReportData();

    reportData.setQtdClient(clients.size());
    reportData.setQtdSalesmen(salesmen.size());
    reportData.setBestSaleId(
        sales.stream().max(Comparator.comparingDouble(Sale::getTotalSale)).get().getId());

    Map<String, Double> result =
        sales.stream()
            .collect(
                Collectors.groupingBy(
                    sale -> sale.getSalesmanName(), Collectors.summingDouble(Sale::getTotalSale)));

    Map.Entry<String, Double> min =
        Collections.min(result.entrySet(), Comparator.comparing(Map.Entry::getValue));

    reportData.setWorstSalesman(min.getKey());

    return reportData;
  }

  public void generateFileReport(ReportData reportData, File file) throws BusinessException {

    try {
      Path path = Paths.get(Parameters.PATH_OUT);

      // if directory exists?
      if (!Files.exists(path)) {
        Files.createDirectories(path);
      }

      String fileName = file.getName().split("[.]")[0] + ".done.dat";

      File permfile = new File(Parameters.PATH_OUT, fileName);
      if (!permfile.exists()) {
        permfile.createNewFile();
      }

      try (BufferedWriter output = new BufferedWriter(new FileWriter(permfile))) {

        List<String> lines = new ArrayList<>();
        lines.add("Quantidade de clientes: " + reportData.getQtdClient());
        lines.add("Quantidade de vendendores: " + reportData.getQtdSalesmen());
        lines.add("Id da Venda mais cara: " + reportData.getBestSaleId());
        lines.add("Pior Vendedor: " + reportData.getWorstSalesman());

        output.write("Relatório de dados gerados a partir do arquivo: " + file.getAbsolutePath());
        output.newLine();
        output.newLine();

        for (String s : lines) {
          output.write(s);
          output.newLine();
        }

      } catch (Exception e) {
        throw new BusinessException("Erro na geração do relatório: " + e.getMessage());
      }

    } catch (Exception k) {
      throw new BusinessException("Erro na geração do relatório: " + k.getMessage());
    }
  }
}
