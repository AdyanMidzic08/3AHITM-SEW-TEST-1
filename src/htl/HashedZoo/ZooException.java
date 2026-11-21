package htl.HashedZoo;

public class ZooException extends RuntimeException {
    ZooException(String message) {
        super(message);
    }

    ZooException(String message, Throwable throwable) {
        super(message,throwable);
    }
}
