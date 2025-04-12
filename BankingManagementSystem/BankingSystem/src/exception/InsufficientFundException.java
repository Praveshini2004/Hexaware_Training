package exception;

public class InsufficientFundException extends Exception {
    
    // Constructor accepting a custom error message
    public InsufficientFundException(String message) {
        super(message); // Call the parent class (Exception) constructor with the message
    }
}
