use hmbank;

SELECT c.customer_id, c.first_name, c.last_name, a.balance
FROM Customers c
JOIN Accounts a ON c.customer_id = a.customer_id
WHERE a.balance = (SELECT MAX(balance) FROM Accounts);

SELECT AVG(balance) AS avg_balance
FROM Accounts
WHERE customer_id IN (
    SELECT customer_id FROM Accounts GROUP BY customer_id HAVING COUNT(account_id) > 1
);

SELECT DISTINCT t.account_id, t.amount
FROM Transactions t
WHERE t.amount > (SELECT AVG(amount) FROM Transactions);

SELECT c.customer_id, c.first_name, c.last_name
FROM Customers c
WHERE c.customer_id NOT IN (
    SELECT DISTINCT a.customer_id FROM Accounts a
    JOIN Transactions t ON a.account_id = t.account_id
);

SELECT SUM(balance) AS total_balance
FROM Accounts
WHERE account_id NOT IN (SELECT DISTINCT account_id FROM Transactions);

SELECT t.*
FROM Transactions t
WHERE t.account_id IN (
    SELECT account_id FROM Accounts WHERE balance = (SELECT MIN(balance) FROM Accounts)
);

SELECT customer_id
FROM Accounts
GROUP BY customer_id
HAVING COUNT(DISTINCT account_type) > 1;

SELECT account_type, 
       (COUNT(*) * 100.0 / (SELECT COUNT(*) FROM Accounts)) AS percentage
FROM Accounts
GROUP BY account_type;

SELECT t.*
FROM Transactions t
JOIN Accounts a ON t.account_id = a.account_id
WHERE a.customer_id =1;

SELECT account_type, 
       (SELECT SUM(balance) FROM Accounts a2 WHERE a1.account_type = a2.account_type) AS total_balance
FROM Accounts a1
GROUP BY account_type;
