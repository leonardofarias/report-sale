package com.ss.reportsale.model;

public class Salesman extends Person {

  private String cpf;
  private Double Salary;

  public Salesman(String name, String cpf, Double salary) {
    super(name);
    this.cpf = cpf;
    Salary = salary;
  }

  public String getCpf() {
    return cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  public Double getSalary() {
    return Salary;
  }

  public void setSalary(Double salary) {
    Salary = salary;
  }
}
