package spring.tutorial.exception;

public class NoStockFoundException extends RuntimeException {
    public NoStockFoundException() {
        super("No Stock Was found");
    }

}
