/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Banking;

/**
 *
 * @author DELL
 */


import java.time.LocalDateTime;

public class Transaction {
    private static long lastTransactionId = 1000;
    private long transactionId;
    private long accountNumber;
    private String transactionType; 
    private String description;
    private LocalDateTime timestamp;
    private float transactionAmount;

    
    public Transaction(long accountNumber, String transactionType, String description, float transactionAmount) {
        this.transactionId = ++lastTransactionId;
        this.accountNumber = accountNumber;
        this.transactionType = transactionType;
        this.description = description;
        this.transactionAmount = transactionAmount;
        this.timestamp = LocalDateTime.now();
    }

   
    public Transaction(long transactionId, long accountNumber, String transactionType, String description,
                       float transactionAmount, LocalDateTime timestamp) {
        this.transactionId = transactionId;
        this.accountNumber = accountNumber;
        this.transactionType = transactionType;
        this.description = description;
        this.transactionAmount = transactionAmount;
        this.timestamp = timestamp;
    }

    public long getTransactionId() {
        return transactionId;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public float getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionId(long transactionId) {
        this.transactionId = transactionId;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public void setTransactionAmount(float transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    @Override
    public String toString() {
        return "Transaction {" +
                "Transaction ID = " + transactionId +
                ", Account Number = " + accountNumber +
                ", Type = '" + transactionType + '\'' +
                ", Amount = ₹" + transactionAmount +
                ", Description = '" + description + '\'' +
                ", Date/Time = " + timestamp +
                '}';
    }
}
