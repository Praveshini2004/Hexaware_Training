CREATE DATABASE BankSystem;

USE BankSystem;



-- Customer Table
CREATE TABLE Customer (
    customerid INT PRIMARY KEY,
    firstname VARCHAR(50),
    lastname VARCHAR(50),
    email VARCHAR(100),
    phonenumber VARCHAR(15),
    address VARCHAR(255)
);

-- Account Table
CREATE TABLE Account (
    accountid INT PRIMARY KEY,
    customerid INT,
    accounttype VARCHAR(20),
    balance FLOAT,
    interestRate FLOAT DEFAULT 4.0,         
    overdraftLimit FLOAT DEFAULT 10000,     
    FOREIGN KEY (customerid) REFERENCES Customer(customerid)
);

-- Transactions Table
CREATE TABLE Transactions (
    transactionId INT AUTO_INCREMENT PRIMARY KEY,
    accountid INT,
    transactiontype VARCHAR(20),
    description TEXT,
    amount FLOAT,
    transactiondate DATETIME,
    FOREIGN KEY (accountid) REFERENCES Account(accountid)
);

select * from customer;
select * from account;
Select * from transactions;
