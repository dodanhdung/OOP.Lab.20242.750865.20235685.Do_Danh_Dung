package hust.soict.hedspi.aims.exception;

public class LimitExceededException extends Exception {
    public LimitExceededException() {
        super("The number of media has exceeded the limit.");
    }

    public LimitExceededException(String message) {
        super(message);
    }
}