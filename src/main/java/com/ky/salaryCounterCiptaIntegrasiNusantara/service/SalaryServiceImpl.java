package com.ky.salaryCounterCiptaIntegrasiNusantara.service;

import com.ky.salaryCounterCiptaIntegrasiNusantara.model.SalaryRequest;
import org.springframework.stereotype.Service;

@Service
public class SalaryServiceImpl implements SalaryService {
    private final long K1 = 75000000;
    private final long K0 = 50000000;
    private final long TK = 25000000;
    private final long TAX_UNDER_50 = 2500000;
    private final long TAX_50_TO_250 = 20000000;
    private final long IDR_250_MIL = 250000000;
    private final long IDR_50_MIL = 50000000;
    private final long TAX_MARIED = 30000000;
    private final long TAX_SINGLE = 15000000;

    public long getSpecificSalary(SalaryRequest request, String key){
        int result = 0;
        for(int i=0 ; i <  request.getSalaryComponents().size() ; i++){
            if (request.getSalaryComponents().get(i).getName().equalsIgnoreCase(key)){
                result = request.getSalaryComponents().get(i).getAmount();
            }
        }
        return result;
    }

    @Override
    public long countMonthSalary(SalaryRequest request) {
       return getSpecificSalary(request, "gaji pokok");
    }

    @Override
    public long countTax(SalaryRequest request) {
        long getMonthSalary = countMonthSalary(request);
        long getPtkp = 0;
        long totalTax = 0;

        //calculate PTKP
        if (request.getEmployee().getMaritalStatus().equalsIgnoreCase("maried")){
            getPtkp = (request.getEmployee().getChilds() > 0) ? K1 : K0;}
        else { getPtkp = TK; }

        switch (request.getEmployee().getCountry().toLowerCase()){
            case "indonesia":
                if (request.getEmployee().getMaritalStatus().equalsIgnoreCase("maried")){
                    getPtkp = (request.getEmployee().getChilds() > 0) ? K1 : K0;}
                else { getPtkp = TK; }
                totalTax = indonesianRules(getMonthSalary, getPtkp);
                break;
            case "vietnam":
                if (request.getEmployee().getMaritalStatus().equalsIgnoreCase("maried")){
                    getPtkp = TAX_MARIED;
                }
                else { getPtkp = TAX_SINGLE; }
                totalTax = vietnamRules(getMonthSalary, getPtkp, getSpecificSalary(request, "asuransi"));
                break;
        }
        return totalTax;
    }

    public long indonesianRules(long monthSalary, long ptkp){
        long nettoInYear = (monthSalary * 12) - ptkp;
        long taxInYear = TAX_UNDER_50;
        double taxInMonth = 0;

        if (nettoInYear > IDR_50_MIL && nettoInYear <= IDR_250_MIL){ //RANGE 50 - 250
            taxInYear += (nettoInYear - IDR_50_MIL) * 10/100;
        }
        else if (nettoInYear > IDR_250_MIL) { //ABOVE 250 MIL
            taxInYear += (nettoInYear - IDR_250_MIL) *  15 / 100 + TAX_50_TO_250;
        }

        taxInMonth = (taxInYear / 12);
        long result = (long) Math.ceil(taxInMonth / 1000) * 1000;
        return result;
    }

    public long vietnamRules(long monthSalary, long ptkp, long insurance){
        long nettoInYear = (monthSalary * 12) - (insurance * 12) - ptkp;
        long taxInYear = 1250000;
        double taxInMonth = 0;

        if (nettoInYear > 50000000){
            taxInYear += (nettoInYear - 50000000) * 7.5 /100;
        }
        taxInMonth = (taxInYear / 12);
        long result = (long) Math.ceil(taxInMonth / 1000) * 1000;
        return result;
    }


}
