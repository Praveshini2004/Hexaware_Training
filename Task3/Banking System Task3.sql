Use hmbank;
SELECT AVG(balance) AS average_balance FROM Accounts;

SELECT * FROM Accounts ORDER BY balance DESC LIMIT 10;

SELECT SUM(amount) AS total_deposits 
FROM Transactions 
WHERE transaction_type = 'deposit' AND transaction_date = '2024-03-05';

SELECT * FROM Customers ORDER BY DOB ASC LIMIT 1; 

SELECT * FROM Customers ORDER BY DOB DESC LIMIT 1; 

SELECT T.*, A.account_type 
FROM Transactions T 
JOIN Accounts A ON T.account_id = A.account_id;

SELECT C.customer_id, C.first_name, C.last_name, A.account_id, A.account_type, A.balance 
FROM Customers C 
JOIN Accounts A ON C.customer_id = A.customer_id;

SELECT T.*, C.first_name, C.last_name, C.email 
FROM Transactions T 
JOIN Accounts A ON T.account_id = A.account_id 
JOIN Customers C ON A.customer_id = C.customer_id 
WHERE A.account_id = '103';

SELECT customer_id, COUNT(account_id) AS account_count 
FROM Accounts 
GROUP BY customer_id 
HAVING COUNT(account_id) > 1;

INSERT INTO Accounts (customer_id, account_type, balance) 
VALUES 
(1, 'current', 2500);
INSERT INTO Accounts (customer_id, account_type, balance) 
VALUES 
(1, 'current', 2500);
INSERT INTO Accounts (customer_id, account_type, balance) 
VALUES 
(2, 'savings', 3500);

Select * from Accounts;
delete from accounts where account_id=112;

SELECT account_id, 
       SUM(CASE WHEN transaction_type = 'deposit' THEN amount ELSE 0 END) - 
       SUM(CASE WHEN transaction_type = 'withdrawal' THEN amount ELSE 0 END) AS balance_difference
FROM Transactions 
GROUP BY account_id;

SELECT account_id, AVG(balance) AS avg_daily_balance 
FROM Accounts 
WHERE account_id IN (
    SELECT DISTINCT account_id FROM Transactions 
    WHERE transaction_date BETWEEN '2024-02-10' AND '2024-09-26'
)
GROUP BY account_id;

SELECT account_type, SUM(balance) AS total_balance 
FROM Accounts 
GROUP BY account_type;

INSERT INTO Transactions (transaction_id, account_id, transaction_type, amount, transaction_date) 
VALUES 
(11, 101, 'withdrawal', 200, '2024-11-05'),
(12, 101, 'deposit', 1000, '2024-11-10'),
(13, 103, 'withdrawal', 150, '2024-11-15'),
(14, 101, 'deposit', 2500, '2024-11-20'),
(15, 103, 'withdrawal', 50, '2024-11-25');

SELECT account_id, COUNT(transaction_id) AS transaction_count 
FROM Transactions 
GROUP BY account_id 
ORDER BY transaction_count DESC;

SELECT C.customer_id, C.first_name, C.last_name, A.account_type, SUM(A.balance) AS total_balance
FROM Customers C 
JOIN Accounts A ON C.customer_id = A.customer_id 
GROUP BY C.customer_id, A.account_type 
HAVING SUM(A.balance) > 1000; 

INSERT INTO Transactions (account_id, transaction_type, amount, transaction_date) 
VALUES 
(101, 'deposit', 500, '2024-01-15'),
(102, 'withdrawal', 700, '2024-02-10');


SELECT account_id, transaction_date, amount, COUNT(*) AS duplicate_count
FROM Transactions
GROUP BY account_id, transaction_date, amount
HAVING COUNT(*) > 1;









