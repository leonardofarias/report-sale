package com.ss.reportsale.data;

import com.ss.reportsale.exception.BusinessException;
import com.ss.reportsale.model.Client;
import com.ss.reportsale.model.Salesman;
import com.ss.reportsale.service.DataService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import sun.awt.SunHints;

import java.util.Objects;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class SalesmanTest {

  @Test
  public void shouldReturnFalseIfSalesmanIsNotCorrect() throws BusinessException {
    DataService dataService = new DataService();

    assertTrue(
        Objects.nonNull(dataService.createSalesman("001ç01773449036çPedroç400.66".split("ç"))));
    assertTrue(
        Objects.nonNull(dataService.createSalesman("001ç01773449036çPedroç400.66".split("ç"))));
    assertFalse(
        Objects.isNull(dataService.createSalesman("001ç01773449036çPedroç50ç0.66".split("ç"))));
  }

  @Test
  public void shouldReturnValidSalesmanWithGivenText() throws BusinessException {
    DataService dataService = new DataService();

    Salesman salesman = dataService.createSalesman("001ç01773449036çPedroç400.66".split("ç"));

    assertEquals("01773449036", salesman.getCpf());
    assertEquals("Pedro", salesman.getName());
    assertEquals("400.66", salesman.getSalary().toString());
  }
}
