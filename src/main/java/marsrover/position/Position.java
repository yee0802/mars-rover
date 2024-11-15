package marsrover.position;

import marsrover.direction.Direction;

public class Position {
    private int x;
    private int y;
    private Direction direction;

    public Position(int x, int y, Direction direction) {
        if (x < 0 || y < 0) {
            throw new IllegalArgumentException("Coordinates cannot be negative.");
        }

        if (direction == null) {
            throw new IllegalArgumentException("Direction cannot be null.");
        }

        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Position calculateNextPosition() {
        Position nextPosition = new Position(this.x, this.y, this.direction);

        switch (direction) {
            case N -> nextPosition.setY(nextPosition.getY() + 1);
            case E -> nextPosition.setX(nextPosition.getX() + 1);
            case S -> nextPosition.setY(nextPosition.getY() - 1);
            case W -> nextPosition.setX(nextPosition.getX() - 1);
        }

        return nextPosition;
    }

    public void moveForward() {
        switch (direction) {
            case N -> y++;
            case E -> x++;
            case S -> y--;
            case W -> x--;
        }
    }

    public String formatCoordinates() {
        return String.format("%s %s", x, y);
    }

    public String formatPosition() {
        return String.format("%s %s %s", x, y, direction);
    }
}