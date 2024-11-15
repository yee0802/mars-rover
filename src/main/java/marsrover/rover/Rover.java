package marsrover.rover;

import marsrover.command.Command;
import marsrover.direction.Direction;
import marsrover.exceptions.*;
import marsrover.position.Position;
import marsrover.plateau.Plateau;

import java.util.List;

public class Rover {
    private Position position;
    private Plateau plateau;
    private final String name;

    public Rover(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Rover name cannot be null or empty.");
        }

        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void landOnPlateau(Plateau plateau, Position position) {
        if (this.plateau != null || this.position != null) {
            throw new RoverAlreadyLandedException("Cannot initiate launch sequence when rover is already landed.");
        }

        this.plateau = plateau;
        this.position = position;

        plateau.addRover(this);
    }

    public String executeCommands(List<Command> commands) {
        for (Command cmd : commands) {
            switch (cmd) {
                case L -> turnLeft();
                case R -> turnRight();
                case M -> move();
            }
        }

        return position.formatPosition();
    }

    private void turnRight() {
        switch (position.getDirection()) {
            case N -> position.setDirection(Direction.E);
            case E -> position.setDirection(Direction.S);
            case S -> position.setDirection(Direction.W);
            case W -> position.setDirection(Direction.N);
        };
    }

    private void turnLeft() {
        switch (position.getDirection()) {
            case N -> position.setDirection(Direction.W);
            case E -> position.setDirection(Direction.N);
            case S -> position.setDirection(Direction.E);
            case W -> position.setDirection(Direction.S);
        };
    }

    private void move() {
        Position newPosition = position.calculateNextPosition();

        if (!plateau.isPositionInBounds(newPosition)) {
            throw new PositionOutOfBoundsException("Cannot move rover as given position is out of bounds.");
        }

        if (!plateau.isPositionEmpty(newPosition)) {
            throw new RoverCollisionException("Cannot move Rover as given position is occupied.");
        }

        position.moveForward();
    }

    public String reportPosition() {
        return position.formatPosition();
    }

    public boolean hasPosition(Position p) {
        if (p == null) {
            throw new IllegalArgumentException("Position cannot be null.");
        }

        return this.position.formatCoordinates().equals(p.formatCoordinates());
    }

    @Override
    public String toString() {
        return "Rover{" +
                "position=" + position +
                ", plateau=" + plateau +
                ", name='" + name + '\'' +
                '}';
    }
}
