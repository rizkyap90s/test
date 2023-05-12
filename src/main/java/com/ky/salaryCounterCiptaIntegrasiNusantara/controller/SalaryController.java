package com.ky.salaryCounterCiptaIntegrasiNusantara.controller;

import com.ky.salaryCounterCiptaIntegrasiNusantara.model.SalaryRequest;
import com.ky.salaryCounterCiptaIntegrasiNusantara.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class SalaryController {

    @Autowired
    SalaryService salaryService;

    @PostMapping("/hitungpajak")
    public long countTax(@RequestBody SalaryRequest request){
        return salaryService.countTax(request);
    }

    @PostMapping("/gajinettobulanan")
    public long countMonthSalary(@RequestBody SalaryRequest request){
        return salaryService.countMonthSalary(request);
    }
}
