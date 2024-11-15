package marsrover.exceptions;

public class RoverCollisionException extends RuntimeException {
    public RoverCollisionException() {
    }

    public RoverCollisionException(String message) {
        super(message);
    }
}
