/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package payrollmanagementsystem;

import java.time.LocalDate;

/**
 *
 * @author DELL
 */

public class Tax {
    private int taxId;
    private int employeeId;
    private int taxYear;
    private double taxableIncome;
    private double taxAmount;

    public Tax(int taxId, int employeeId, int taxYear, double taxableIncome, double taxAmount) {
        this.taxId = taxId;
        this.employeeId = employeeId;
        this.taxYear = taxYear;
        this.taxableIncome = taxableIncome;
        this.taxAmount = taxAmount;
    }

    public int getTaxId() {
        return taxId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public int getTaxYear() {
        return taxYear;
    }

    public double getTaxableIncome() {
        return taxableIncome;
    }

    public double getTaxAmount() {
        return taxAmount;
    }

    public void setTaxId(int taxId) {
        this.taxId = taxId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public void setTaxYear(int taxYear) {
        this.taxYear = taxYear;
    }

    public void setTaxableIncome(double taxableIncome) {
        this.taxableIncome = taxableIncome;
    }

    public void setTaxAmount(double taxAmount) {
        this.taxAmount = taxAmount;
    }
}
