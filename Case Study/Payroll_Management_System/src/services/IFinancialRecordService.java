package services;

import Exceptions.FinancialRecordException;
import payrollmanagementsystem.FinancialRecord;
import java.util.List;

public interface IFinancialRecordService {
    void addFinancialRecord(FinancialRecord record) throws FinancialRecordException;
    FinancialRecord getFinancialRecordById(int recordId) throws FinancialRecordException;
    List<FinancialRecord> getFinancialRecordsForEmployee(int employeeId);
}