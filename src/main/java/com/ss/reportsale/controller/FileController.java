package com.ss.reportsale.controller;

import com.ss.reportsale.service.ProcessorService;
import com.ss.reportsale.service.ReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import java.io.*;
import java.util.*;

@Controller
public class FileController {

  private final Logger log = LoggerFactory.getLogger(FileController.class);
  private ProcessorService processorService;

  public FileController() {
    this.processorService = new ProcessorService();
  }

  public void listFiles() {

    try {

      List<File> files = processorService.listFiles();

      if(files.size() > 0){
        log.info("Pasta Encontrada, lendo arquivo(s).");

        for(File file : files){
          processorService.readFile(file);
        }

      }else{
        log.info("Arquivos não Encontrados.");
      }

    } catch (IOException e) {
      log.error(e.getMessage());

    } catch (Exception e) {
      log.error(e.getMessage());
    }
  }

}
