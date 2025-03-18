USE HMBank;
INSERT INTO Customers (customer_id, first_name, last_name, DOB, email, phone_number, address) 
VALUES 
(1, 'John', 'Doe', '1985-07-10', 'john.doe@example.com', '9876543210', 'New York'),
(2, 'Alice', 'Smith', '1990-03-15', 'alice.smith@example.com', '9823456789', 'Los Angeles'),
(3, 'Bob', 'Johnson', '1988-11-20', 'bob.johnson@example.com', '9845671234', 'Chicago'),
(4, 'Charlie', 'Brown', '1992-06-25', 'charlie.brown@example.com', '9876543123', 'Houston'),
(5, 'David', 'Williams', '1995-09-18', 'david.williams@example.com', '9887654321', 'Phoenix'),
(6, 'Eve', 'Miller', '1993-12-01', 'eve.miller@example.com', '9812345678', 'Dallas'),
(7, 'Frank', 'Wilson', '1997-02-17', 'frank.wilson@example.com', '9898765432', 'San Diego'),
(8, 'Grace', 'Moore', '1989-08-30', 'grace.moore@example.com', '9854321098', 'Austin'),
(9, 'Hannah', 'Taylor', '1991-05-22', 'hannah.taylor@example.com', '9821098765', 'San Francisco'),
(10, 'Isaac', 'Anderson', '1994-04-11', 'isaac.anderson@example.com', '9872105432', 'Seattle');


INSERT INTO Accounts (account_id, customer_id, account_type, balance) 
VALUES 
(101, 1, 'savings', 1500),
(102, 2, 'current', 3000),
(103, 3, 'savings', 500),
(104, 4, 'current', 8000),
(105, 5, 'savings', 0),
(106, 6, 'current', 1200),
(107, 7, 'savings', 2500),
(108, 8, 'current', 6000),
(109, 9, 'savings', 200),
(110, 10, 'current', 900);

INSERT INTO Transactions (transaction_id, account_id, transaction_type, amount, transaction_date) 
VALUES 
(1, 101, 'deposit', 500, '2024-01-15'),
(2, 102, 'withdrawal', 700, '2024-02-10'),
(3, 103, 'deposit', 300, '2024-03-05'),
(4, 104, 'withdrawal', 1000, '2024-04-20'),
(5, 105, 'deposit', 150, '2024-05-30'),
(6, 106, 'withdrawal', 400, '2024-06-12'),
(7, 107, 'deposit', 600, '2024-07-18'),
(8, 108, 'withdrawal', 500, '2024-08-22'),
(9, 109, 'deposit', 350, '2024-09-25'),
(10, 110, 'withdrawal', 200, '2024-10-28');


SELECT c.first_name, c.last_name, a.account_type, c.email 
FROM Customers c 
JOIN Accounts a ON c.customer_id = a.customer_id;

SELECT c.first_name, c.last_name, t.transaction_type, t.amount, t.transaction_date 
FROM Transactions t 
JOIN Accounts a ON t.account_id = a.account_id 
JOIN Customers c ON a.customer_id = c.customer_id;

UPDATE Accounts 
SET balance = balance + 500 
WHERE account_id = 101;

SELECT CONCAT(first_name, ' ', last_name) AS full_name FROM Customers;

SET SQL_SAFE_UPDATES = 0;
DELETE FROM Accounts 
WHERE balance = 0 AND account_type = 'savings';

SELECT * FROM Customers WHERE address = 'New York';

SELECT balance FROM Accounts WHERE account_id = 101;

SELECT * FROM Accounts WHERE account_type = 'current' AND balance > 1000;

SELECT * FROM Transactions WHERE account_id = 102;

SELECT account_id, balance, balance * 0.04 AS interest 
FROM Accounts WHERE account_type = 'savings';

SELECT * FROM Accounts WHERE balance < 500;

SELECT * FROM Customers WHERE address <> 'New York';
