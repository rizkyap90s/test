package com.ky.salaryCounterCiptaIntegrasiNusantara.service;

import com.ky.salaryCounterCiptaIntegrasiNusantara.model.SalaryComponent;
import com.ky.salaryCounterCiptaIntegrasiNusantara.model.SalaryRequest;

import java.util.List;

public interface SalaryService {
    long countTax(SalaryRequest request);
    long countMonthSalary(SalaryRequest request);

}
