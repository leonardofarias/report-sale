package com.ss.reportsale.model;

import java.util.Objects;

public class Salesman {

    private String cpf;
    private String name;
    private Double Salary;

    public Salesman(String cpf, String name, Double salary) {
        this.cpf = cpf;
        this.name = name;
        Salary = salary;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSalary() {
        return Salary;
    }

    public void setSalary(Double salary) {
        Salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Salesman salesman = (Salesman) o;
        return Objects.equals(cpf, salesman.cpf) &&
                Objects.equals(name, salesman.name) &&
                Objects.equals(Salary, salesman.Salary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf, name, Salary);
    }

    @Override
    public String toString() {
        return "Salesman{" +
                "cpf='" + cpf + '\'' +
                ", name='" + name + '\'' +
                ", Salary=" + Salary +
                '}';
    }
}
