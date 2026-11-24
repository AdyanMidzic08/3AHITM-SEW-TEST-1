package htl.sports;

public class SportsEventException extends RuntimeException {
    public SportsEventException(String message) {
        super(message);
    }

    public SportsEventException(String message, Throwable cause) {
        super(message, cause);
    }
}
