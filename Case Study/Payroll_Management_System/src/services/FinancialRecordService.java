package services;

import Exceptions.FinancialRecordException;
import payrollmanagementsystem.FinancialRecord;
import java.util.*;
import java.time.LocalDate;

public class FinancialRecordService implements IFinancialRecordService {
    private List<FinancialRecord> records = new ArrayList<>();

    @Override
    public void addFinancialRecord(FinancialRecord record) throws FinancialRecordException {
        if (record == null) {
            throw new FinancialRecordException("Cannot add a null financial record.");
        }
        records.add(record);
    }

    @Override
    public FinancialRecord getFinancialRecordById(int recordId) throws FinancialRecordException {
        for (FinancialRecord record : records) {
            if (record.getRecordId() == recordId) {
                return record;
            }
        }
        throw new FinancialRecordException("Financial record with ID " + recordId + " not found.");
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

    // Additional helper method (optional)
    public List<FinancialRecord> getFinancialRecordsForDate(LocalDate date) {
        List<FinancialRecord> result = new ArrayList<>();
        for (FinancialRecord record : records) {
            if (record.getRecordDate().equals(date)) {
                result.add(record);
            }
        }
        return result;
    }
}
