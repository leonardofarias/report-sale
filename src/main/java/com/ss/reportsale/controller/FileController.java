package com.ss.reportsale.controller;

import com.ss.reportsale.exception.BusinessException;
import com.ss.reportsale.model.Client;
import com.ss.reportsale.model.ReportData;
import com.ss.reportsale.model.Sale;
import com.ss.reportsale.model.Salesman;
import com.ss.reportsale.service.DataService;
import com.ss.reportsale.service.ProcessorService;
import com.ss.reportsale.service.ReportService;
import com.ss.reportsale.utils.Parameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import java.io.*;
import java.nio.file.Path;
import java.util.*;

@Controller
public class FileController {

  private final Logger log = LoggerFactory.getLogger(FileController.class);
  private final ProcessorService processorService;
  private final ReportService reportService;
  private final DataService dataService;

  public FileController(
      ProcessorService processorService, ReportService reportService, DataService dataService) {
    this.processorService = processorService;
    this.reportService = reportService;
    this.dataService = dataService;
  }

  public void createReport(Path file) throws BusinessException {
    try {
      if (file.toFile().getName().endsWith(".dat")) {
        readFile(file.toFile());
      } else {
        throw new BusinessException(
            "Arquivo: " + file.getFileName() + " não está no formato .dat!");
      }
    } catch (Exception e) {
      throw new BusinessException(e.getMessage());
    }
  }

  private void readFile(File file) {

    try {
      log.info("Processando dados");
      processorService.readFile(file);

      log.info("Gerando relatório");
      ReportData report;

      List<Salesman> salesmen = dataService.getSalesmen();
      List<Client> clients = dataService.getClients();
      List<Sale> sales = dataService.getSales();

      report = reportService.generateOutput(salesmen, clients, sales);

      salesmen.clear();
      clients.clear();
      sales.clear();

      reportService.generateFileReport(report, file, Parameters.PATH_OUT);

      log.info("Relatório gerado a partir do arquivo: " + file.getName());

    } catch (BusinessException e) {
      log.error(e.getMessage());
    }
  }
}
