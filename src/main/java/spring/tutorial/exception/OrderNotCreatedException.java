package spring.tutorial.exception;

public class OrderNotCreatedException extends RuntimeException {
    public OrderNotCreatedException() {
        super("No location could be found");
    }
}
