Create Database Payxpert;
Use Payxpert;

Create Table Employee(
EmployeeId int primary key auto_increment,
FirstName varchar(50),
LastName varchar(50) ,
DateOfBirth date,
Gender varchar(10),
email varchar(100),
PhoneNumber varchar(15),
Address Text,
Position varchar(50),
JoiningDate Date,
TerminationDate date);

Create Table Payroll(
PayrollId int primary key auto_increment ,
EmployeeId int,
PayPeriodStartDate date,
PayPeriodEndDate date,
BasicSalary decimal(10,2),
OverTimePay decimal(10,2),
Deductions decimal(10,2),
NetSalary decimal(10,2),
foreign key(EmployeeId) references Employee(EmployeeId) on delete cascade);

Create Table Tax(
TaxId int primary key auto_increment,
EmployeeId int,
TaxYear int,
TaxableIncome decimal(10,2),
TaxAmount decimal(10,2),
Foreign key(EmployeeId) References Employee(EmployeeId) on delete cascade);

Create Table FinancialRecord(
RecordId int primary key auto_increment,
EmployeeId int,
RecordDate date,
Description text,
Amount decimal(10,2),
RecordType varchar(50),
foreign key(EmployeeId) references employee(EmployeeId) on delete cascade);


select * from financialrecord;
select * from payroll;
select * from employee;
