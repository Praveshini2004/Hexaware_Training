package exception;

public class InvalidAccountException extends Exception {
    
    // Constructor accepting a custom error message
    public InvalidAccountException(String message) {
        super(message); // Call the parent class (Exception) constructor with the message
    }
}
