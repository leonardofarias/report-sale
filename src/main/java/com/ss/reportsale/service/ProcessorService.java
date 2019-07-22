package com.ss.reportsale.service;

import com.ss.reportsale.exception.BusinessException;
import com.ss.reportsale.utils.Parameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.Charset;

@Service
public class ProcessorService {

  private final Logger log = LoggerFactory.getLogger(ProcessorService.class);
  private final DataService dataService;

  public ProcessorService(DataService dataService) {
    this.dataService = dataService;
  }

  public void readFile(File file) throws BusinessException {

    String[] partes;

    try (BufferedReader br =
        new BufferedReader(
            new InputStreamReader(new FileInputStream(file), Charset.forName("ISO-8859-1")))) {
      String strLine;

      log.info("Lendo arquivo: " + file.getName());
      int count = 0;

      while ((strLine = br.readLine()) != null) {

        if (!strLine.isEmpty() && !strLine.trim().equals("")) {
          log.info("Lendo linha " + count + ": " + strLine);
          partes = new String(strLine.getBytes()).split(Parameters.SPLIT_VAR);
          createObjectsForReport(partes);

        } else {
          log.info("Linha " + count + " vazia.");
        }
        count++;
      }

    } catch (Exception e) {
      throw new BusinessException("Erro na leitura dos dados: " + e.getMessage());
    }
  }

  public void createObjectsForReport(String[] partes) throws BusinessException {

    Boolean check = true;
    for (String parte : partes) {
      if (parte.trim().equals("")) {
        check = !check;
      }
    }

    if (check) {
      dataService.create(partes);
    } else {
      throw new BusinessException("Dados incorretos");
    }
  }
}
