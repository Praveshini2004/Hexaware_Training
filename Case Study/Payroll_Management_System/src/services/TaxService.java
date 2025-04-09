package services;
import Exceptions.TaxCalculationException;
import payrollmanagementsystem.Tax;
import java.util.*;

public class TaxService implements ITaxService {
    private List<Tax> taxRecords = new ArrayList<>();

 


    public double calculateTax(double taxableIncome) throws TaxCalculationException {
        if (taxableIncome < 0) {
            throw new TaxCalculationException("Taxable income cannot be negative.");
        }

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

    public void addTaxRecord(Tax tax) {
        taxRecords.add(tax);
    }
}