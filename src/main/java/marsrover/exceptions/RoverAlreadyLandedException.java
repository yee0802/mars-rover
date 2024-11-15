package marsrover.exceptions;

public class RoverAlreadyLandedException extends RuntimeException {
    public RoverAlreadyLandedException() {
    }

    public RoverAlreadyLandedException(String message) {
        super(message);
    }
}
