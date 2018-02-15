package spring.tutorial.exception;

public class OrderNotCreatedException extends Exception {
    public OrderNotCreatedException(String message) {
        super(message);
    }
}
