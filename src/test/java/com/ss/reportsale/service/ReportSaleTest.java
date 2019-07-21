package com.ss.reportsale.service;

import com.ss.reportsale.exception.BusinessException;
import com.ss.reportsale.model.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class ReportSaleTest {

  @Test
  public void shouldReturnReportDataWithGivenInput() throws BusinessException, IOException {
    DataService dataService = new DataService();
    ProcessorService processor = new ProcessorService(dataService);
    ReportService reportService = new ReportService();

    Path file = createFile();

    processor.readFile(file.toFile());

    List<Salesman> salesmen = dataService.getSalesmen();
    List<Client> clients = dataService.getClients();
    List<Sale> sales = dataService.getSales();

    ReportData reportData = reportService.generateOutput(salesmen, clients, sales);

    assertEquals(reportData, (new ReportData(2, 2, 10, "Paulo")));
  }

  @Test
  public void shouldReturnAResumeFileFromASalesReportFile() throws IOException, BusinessException {
    DataService dataService = new DataService();
    ProcessorService processor = new ProcessorService(dataService);
    ReportService reportService = new ReportService();

    Path file = createFile();

    processor.readFile(file.toFile());

    List<Salesman> salesmen = dataService.getSalesmen();
    List<Client> clients = dataService.getClients();
    List<Sale> sales = dataService.getSales();

    ReportData reportData = reportService.generateOutput(salesmen, clients, sales);

    String pathOut = "src/test/resources/data/out/";
    String fileOutput = file.toFile().getName().replace(".dat", ".done.dat");

    reportService.generateFileReport(reportData, file.toFile(), pathOut);

    Path outputFile = Paths.get("src/test/resources/data/out/" + fileOutput);

    String expected =
        new StringBuilder()
            .append("Quantidade de clientes: 2\n")
            .append("Quantidade de vendendores: 2\n")
            .append("Id da Venda mais cara: 10\n")
            .append("Pior Vendedor: Paulo\n")
            .toString();

    assertEquals(expected, new String(Files.readAllBytes(outputFile)));
  }

  private Path createFile() throws IOException {
    String content =
        new StringBuilder()
            .append("001ç1234567891234çPedroç50000\n")
            .append("001ç3245678865434çPauloç40000.99\n")
            .append("002ç2345675434544345çJose da SilvaçRural\n")
            .append("002ç2345675433444345çEduardo PereiraçRural\n")
            .append("003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro\n")
            .append("003ç08ç[1-34-10,2-33-1.50,3-40-0.10]çPaulo")
            .toString();

    Path file =
        Files.write(
            Paths.get("src/test/resources/data/in/dado1.dat"), content.getBytes("ISO-8859-1"));

    return file;
  }
}
