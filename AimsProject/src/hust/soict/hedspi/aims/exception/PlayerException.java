package hust.soict.hedspi.aims.exception;

public class PlayerException extends Exception {
    public PlayerException() {
        super("ERROR: Cannot play this media!");
    }

    public PlayerException(String message) {
        super(message);
    }
}