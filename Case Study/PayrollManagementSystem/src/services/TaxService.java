/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import payrollmanagementsystem.Tax;
import java.util.*;
/**
 *
 * @author DELL
 */
public class TaxService implements ITaxService {
    private List<Tax> taxRecords = new ArrayList<>();

    public double calculateTax(double taxableIncome) {
        // Example tax calculation logic
        if (taxableIncome <= 250000) {
            return 0;
        } else if (taxableIncome <= 500000) {
            return taxableIncome * 0.05;
        } else if (taxableIncome <= 1000000) {
            return taxableIncome * 0.20;
        } else {
            return taxableIncome * 0.30;
        }
    }

    @Override
    public Tax getTaxById(int taxId) {
        for (Tax tax : taxRecords) {
            if (tax.getTaxId() == taxId) {
                return tax;
            }
        }
        return null;
    }

    @Override
    public List<Tax> getTaxesForEmployee(int employeeId) {
        List<Tax> result = new ArrayList<>();
        for (Tax tax : taxRecords) {
            if (tax.getEmployeeId() == employeeId) {
                result.add(tax);
            }
        }
        return result;
    }

    @Override
    public List<Tax> getTaxesForYear(int taxYear) {
        List<Tax> result = new ArrayList<>();
        for (Tax tax : taxRecords) {
            if (tax.getTaxYear() == taxYear) {
                result.add(tax);
            }
        }
        return result;
    }

    // Optional: method to add a tax record
    public void addTaxRecord(Tax tax) {
        taxRecords.add(tax);
    }

//    public Tax calculateTax(int employeeId, int taxYear, double taxableIncome) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

    
   
}