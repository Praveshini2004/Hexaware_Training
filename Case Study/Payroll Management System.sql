CREATE TABLE Employee (
    EmployeeID INT PRIMARY KEY AUTO_INCREMENT,
    FirstName VARCHAR(50),
    LastName VARCHAR(50),
    DateOfBirth DATE,
    Gender VARCHAR(10),
    Email VARCHAR(100) UNIQUE,
    PhoneNumber VARCHAR(15),
    Address TEXT,
    Position VARCHAR(50),
    JoiningDate DATE,
    TerminationDate DATE NULL
);

CREATE TABLE Payroll (
    PayrollID INT PRIMARY KEY AUTO_INCREMENT,
    EmployeeID INT,
    PayPeriodStartDate DATE,
    PayPeriodEndDate DATE,
    BasicSalary DECIMAL(10,2),
    OvertimePay DECIMAL(10,2),
    Deductions DECIMAL(10,2),
    NetSalary DECIMAL(10,2),
    FOREIGN KEY (EmployeeID) REFERENCES Employee(EmployeeID)
);

CREATE TABLE Tax (
    TaxID INT PRIMARY KEY AUTO_INCREMENT,
    EmployeeID INT,
    TaxYear INT,
    TaxableIncome DECIMAL(10,2),
    TaxAmount DECIMAL(10,2),
    FOREIGN KEY (EmployeeID) REFERENCES Employee(EmployeeID)
);

CREATE TABLE FinancialRecord (
    RecordID INT PRIMARY KEY AUTO_INCREMENT,
    EmployeeID INT,
    RecordDate DATE,
    Description VARCHAR(255),
    Amount DECIMAL(10,2),
    RecordType VARCHAR(50),
    FOREIGN KEY (EmployeeID) REFERENCES Employee(EmployeeID)
);
