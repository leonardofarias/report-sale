package com.ss.reportsale.data;

import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import com.ss.reportsale.exception.BusinessException;
import com.ss.reportsale.model.Client;
import com.ss.reportsale.model.Salesman;
import com.ss.reportsale.service.DataService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Objects;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class ClientTest {

  @Test
  public void shouldReturnFalseIfClientIsNotCorrect() throws BusinessException {
    DataService dataService = new DataService();

    assertTrue(
        Objects.nonNull(dataService.createClient("002ç00100200300405çCustomerçBank".split("ç"))));
    assertTrue(
        Objects.nonNull(
            dataService.createClient(
                "002ç00100200300405çCustomer With SpaceçBusiness Area".split("ç"))));
    assertTrue(
        Objects.nonNull(
            dataService.createClient(
                "002ç00100200300405çCustomer Wiçth SpaceçBusiness Area".split("ç"))));
  }

  @Test
  public void shouldReturnValidClientWithGivenText() throws BusinessException {
    DataService dataService = new DataService();

    Client client =
        dataService.createClient("002ç00100200300405çCustomer With SpaceçBusiness Area".split("ç"));

    assertEquals("00100200300405", client.getCnpj());
    assertEquals("Customer With Space", client.getName());
    assertEquals("Business Area", client.getBusinessArea());
  }
}
