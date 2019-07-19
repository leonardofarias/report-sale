package com.ss.reportsale.service;

import com.ss.reportsale.model.Client;
import com.ss.reportsale.model.ReportData;
import com.ss.reportsale.model.Sale;
import com.ss.reportsale.model.Salesman;
import com.ss.reportsale.repository.InputRepository;
import com.ss.reportsale.utils.Parameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ProcessorService {

  private final Logger log = LoggerFactory.getLogger(ProcessorService.class);
  private InputRepository inputRepository;
  private ReportService reportService;

  public ProcessorService() {
    this.inputRepository = new InputRepository();
    this.reportService = new ReportService();
  }

  public List<File> listFiles() throws IOException {

    log.info("Lendo Diretório: " + Parameters.PATH_IN);

    List<File> result;

    try (Stream<Path> walk = Files.list(Paths.get(Parameters.PATH_IN))) {

      result =
          walk.map(x -> x.toFile())
              .filter(f -> f.getName().endsWith(".dat"))
              .collect(Collectors.toList());

      result.forEach(item -> log.info("Arquivo: " + item.getName()));

    } catch (IOException e) {
      throw new IOException(e);
    }
    return result;
  }

  public void readFile(File file) {

    try (BufferedReader br =
        new BufferedReader(
            new InputStreamReader(new FileInputStream(file), Charset.forName("ISO-8859-1")))) {
      String strLine;

      log.info("Lendo arquivo: " + file.getName());
      int count = 0;

      while ((strLine = br.readLine()) != null) {

        if (!strLine.isEmpty() && !strLine.trim().equals("")) {
          log.info("Lendo linha " + count + ": " + strLine);
          String[] partes = strLine.split(Parameters.SPLIT_VAR);

          createObjectsForReport(partes);

        } else {
          log.info("Linha " + count + " vazia.");
        }
        count++;
      }

      ReportData report;

      List<Salesman> salesmen = inputRepository.getSalesmen();
      List<Client> clients = inputRepository.getClients();
      List<Sale> sales = inputRepository.getSales();

      report = reportService.generateOutput(salesmen, clients, sales);

      reportService.generateFileReport(report, file);
    } catch (IOException e) {
      log.error(e.getMessage());
    }
  }

  private void createObjectsForReport(String[] partes) {

    Boolean check = true;
    for (String parte : partes) {
      if (parte.trim().equals("")) {
        check = !check;
      }
    }

    if (check) {
      inputRepository.create(partes);
    } else {
      log.info("Dados incorretos");
    }
  }
}
