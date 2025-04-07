/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

/**
 *
 * @author DELL
 */
import java.time.*;
import payrollmanagementsystem.FinancialRecord;
public class ValidationService {

    /**
     * Validates a FinancialRecord before adding it to the system.
     *
     * @param record the financial record to validate
     * @throws IllegalArgumentException if any field is invalid
     */
    public void validateFinancialRecord(FinancialRecord record) {
        if (record == null) {
            throw new IllegalArgumentException("Financial record cannot be null.");
        }

        if (record.getEmployeeId() <= 0) {
            throw new IllegalArgumentException("Invalid employee ID.");
        }

        if (record.getRecordDate() == null || record.getRecordDate().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Record date cannot be null or in the future.");
        }

        if (record.getAmount() <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero.");
        }

        if (record.getRecordType() == null || record.getRecordType().trim().isEmpty()) {
            throw new IllegalArgumentException("Record type cannot be empty.");
        }

        if (record.getDescription() == null || record.getDescription().trim().isEmpty()) {
            throw new IllegalArgumentException("Description cannot be empty.");
        }
    }

    /**
     * Validates a tax percentage (0–100).
     *
     * @param taxPercentage the tax percentage to validate
     * @throws IllegalArgumentException if out of valid range
     */
    public void validateTaxPercentage(double taxPercentage) {
        if (taxPercentage < 0 || taxPercentage > 100) {
            throw new IllegalArgumentException("Tax percentage must be between 0 and 100.");
        }
    }

    
}