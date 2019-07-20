package com.ss.reportsale;

import com.ss.reportsale.controller.FileController;
import com.ss.reportsale.exception.BusinessException;
import com.ss.reportsale.utils.Parameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.nio.file.*;

import static java.nio.file.StandardWatchEventKinds.*;

@SpringBootApplication
public class ReportSaleApplication {

  private static Logger log = LoggerFactory.getLogger(ReportSaleApplication.class);

  private static FileController fileController = null;

  @Autowired
  public ReportSaleApplication(FileController fileController) {
    this.fileController = fileController;
  }

  public static void main(String[] args) throws InterruptedException {
    SpringApplication.run(ReportSaleApplication.class, args);

    try {

      log.info("Iniciando aplicação");

      Path dir = Paths.get(Parameters.PATH_IN);
      WatchService watcher = FileSystems.getDefault().newWatchService();
      WatchKey key = dir.register(watcher, ENTRY_CREATE, ENTRY_MODIFY);

      while ((key = watcher.take()) != null) {
        for (WatchEvent<?> event : key.pollEvents()) {
          Path file = dir.resolve((Path) event.context());
          fileController.createReport(file);
        }
        key.reset();
      }

    } catch (InterruptedException e) {
      log.error(e.getMessage());
    } catch (IOException x) {
      log.error(x.getMessage());
    } catch (BusinessException e) {
      log.error(e.getMessage());
    }
  }
}
