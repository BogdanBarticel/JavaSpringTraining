package java.spring.tutorial.exceptions;

public class LocationNotFoundForProductException extends Exception {
    public LocationNotFoundForProductException (String message) {
        super(message);
    }

}
