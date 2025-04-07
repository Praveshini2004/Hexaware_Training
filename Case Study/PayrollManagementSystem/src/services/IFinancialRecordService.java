/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import payrollmanagementsystem.FinancialRecord;
import java.util.*;
import java.time.LocalDate;

/**
 *
 * @author DELL
 */public interface IFinancialRecordService {
    void addFinancialRecord(FinancialRecord record);
    FinancialRecord getFinancialRecordById(int recordId);
    List<FinancialRecord> getFinancialRecordsForEmployee(int employeeId);
    List<FinancialRecord> getFinancialRecordsForDate(LocalDate date); // Changed from Date to LocalDate
}