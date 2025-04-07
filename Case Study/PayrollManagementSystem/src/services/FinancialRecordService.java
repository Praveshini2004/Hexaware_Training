/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import java.util.*;
import payrollmanagementsystem.FinancialRecord;
import java.time.LocalDate;
/**
 *
 * @author DELL
 */
public class FinancialRecordService implements IFinancialRecordService {
    private List<FinancialRecord> records = new ArrayList<>();

    @Override
    public void addFinancialRecord(FinancialRecord record) {
        records.add(record);
    }

    @Override
    public FinancialRecord getFinancialRecordById(int recordId) {
        for (FinancialRecord record : records) {
            if (record.getRecordId() == recordId) {
                return record;
            }
        }
        return null;
    }

    @Override
    public List<FinancialRecord> getFinancialRecordsForEmployee(int employeeId) {
        List<FinancialRecord> result = new ArrayList<>();
        for (FinancialRecord record : records) {
            if (record.getEmployeeId() == employeeId) {
                result.add(record);
            }
        }
        return result;
    }

    public List<FinancialRecord> getFinancialRecordsForDate(LocalDate date) {
    List<FinancialRecord> result = new ArrayList<>();
    for (FinancialRecord record : records) {
        if (record.getDate().equals(date)) {
            result.add(record);
        }
    }
    return result;
}
}