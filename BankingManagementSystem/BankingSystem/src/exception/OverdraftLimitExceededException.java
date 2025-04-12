package exception;

public class OverdraftLimitExceededException extends Exception {

    // Constructor accepting a custom error message
    public OverdraftLimitExceededException(String message) {
        super(message); // Call the parent class (Exception) constructor with the message
    }
}
