package com.ss.reportsale.controller;

import com.ss.reportsale.exception.BusinessException;
import com.ss.reportsale.model.Client;
import com.ss.reportsale.model.ReportData;
import com.ss.reportsale.model.Sale;
import com.ss.reportsale.model.Salesman;
import com.ss.reportsale.service.DataService;
import com.ss.reportsale.service.ProcessorService;
import com.ss.reportsale.service.ReportService;
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
    try{
      readFile(file.toFile());
    }catch (Exception e ){
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

      reportService.generateFileReport(report, file);

      dataService.setSalesmen(new ArrayList<>());
      dataService.setClients(new ArrayList<>());
      dataService.setSales(new ArrayList<>());

      log.info("Relatório gerado a partir do arquivo: " + file.getName());

    } catch (BusinessException e) {
      log.error(e.getMessage());
    }
  }
}
