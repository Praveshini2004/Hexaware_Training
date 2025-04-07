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
 */public class FinancialRecord {
    private int recordId;
    private int employeeId;
    private LocalDate recordDate;
    private String description;
    private double amount;
    private String recordType;

    public FinancialRecord() {}

    public FinancialRecord(int recordId, int employeeId, LocalDate recordDate, String description,
                           double amount, String recordType) {
        this.recordId = recordId;
        this.employeeId = employeeId;
        this.recordDate = recordDate;
        this.description = description;
        this.amount = amount;
        this.recordType = recordType;
    }

    public int getRecordId() { return recordId; }
    public void setRecordId(int recordId) { this.recordId = recordId; }

    public int getEmployeeId() { return employeeId; }
    public void setEmployeeId(int employeeId) { this.employeeId = employeeId; }

    public LocalDate getRecordDate() { return recordDate; }
    public void setRecordDate(LocalDate recordDate) { this.recordDate = recordDate; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public String getRecordType() { return recordType; }
    public void setRecordType(String recordType) { this.recordType = recordType; }
    
    public LocalDate getDate() {
        return this.recordDate;
    }

    

    
}