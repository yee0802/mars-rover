package marsrover.exceptions;

public class PositionOutOfBoundsException extends RuntimeException {
    public PositionOutOfBoundsException() {
    }

    public PositionOutOfBoundsException(String message) {
        super(message);
    }
}
