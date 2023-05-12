package com.ky.salaryCounterCiptaIntegrasiNusantara.model;

import java.util.ArrayList;
import java.util.List;

public class SalaryRequest {
    private Employee employee;
    private List<SalaryComponent> salaryComponents = new ArrayList<>();

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public List<SalaryComponent> getSalaryComponents() {
        return salaryComponents;
    }

    public void setSalaryComponents(List<SalaryComponent> salaryComponents) {
        this.salaryComponents = salaryComponents;
    }
}
