package spring.tutorial.exception;

public class NoStockFoundException extends Exception {
    public NoStockFoundException(String message) {
        super(message);
    }

}
