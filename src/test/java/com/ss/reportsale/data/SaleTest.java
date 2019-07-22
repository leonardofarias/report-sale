package com.ss.reportsale.data;

import com.ss.reportsale.exception.BusinessException;
import com.ss.reportsale.model.Client;
import com.ss.reportsale.model.Product;
import com.ss.reportsale.model.Sale;
import com.ss.reportsale.service.DataService;
import org.junit.Test;

import java.util.Objects;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.*;

public class SaleTest {

  @Test(expected = Exception.class)
  public void shouldReturnFalseIfClientIsNotCorrect() throws BusinessException {
    DataService dataService = new DataService();

    assertTrue(
        Objects.nonNull(dataService.createSale("003ç123ç[1-10-100,2-30-2.50,3-40-3.10]çSalesmanName".split("ç"))));
    assertTrue(
        Objects.nonNull(
            dataService.createSale(
                "003ç123ç[1-10-100,2-30-2.50,3-40-3.10]çSalesmanName".split("ç"))));
    assertFalse(
        Objects.nonNull(
            dataService.createSale(
                "003ç123ççSalesmçanName".split("ç"))));
  }

  @Test
  public void shouldReturnValidClientWithGivenText() throws BusinessException {
    DataService dataService = new DataService();

    Sale sale =
        dataService.createSale("003ç123ç[1-10-100,2-30-2.50,3-40-3.10]çSalesmanName".split("ç"));

    assertEquals("123", sale.getId().toString());
    assertEquals("SalesmanName", sale.getSalesmanName());
    assertTrue(containsInAnyOrder(
      new Product(1, 10, 100D),
      new Product(2, 30, 2.50),
      new Product(3, 40, 3.10)).matches(sale.getProducts()));
  }
}
