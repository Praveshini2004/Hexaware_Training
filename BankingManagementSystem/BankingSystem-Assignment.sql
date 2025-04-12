CREATE DATABASE Bank;
USE Bank;

CREATE TABLE customers (
    customerId INT PRIMARY KEY AUTO_INCREMENT,
    firstName VARCHAR(100) NOT NULL,
    lastName VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phoneNumber VARCHAR(20),
    address VARCHAR(255)
);
CREATE TABLE accounts (
    accountNumber VARCHAR(20) PRIMARY KEY,
    customerId INT,
    balance DOUBLE NOT NULL DEFAULT 0.0,
    accountType ENUM('Current', 'Savings', 'ZeroBalance') NOT NULL,
    FOREIGN KEY (customerId) REFERENCES customers(customerId) ON DELETE CASCADE
);
CREATE TABLE transactions (
    transactionId INT AUTO_INCREMENT PRIMARY KEY,
    accountNumber VARCHAR(20),
    transactionType VARCHAR(20),
    amount DOUBLE,
    transactionDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

