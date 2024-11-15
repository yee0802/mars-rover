package marsrover.plateau;

import marsrover.position.Position;
import marsrover.rover.Rover;

import java.util.ArrayList;
import java.util.List;

public class Plateau {
    private final PlateauSize plateauSize;
    private List<Rover> activeRovers;

    public Plateau(PlateauSize size) {
        this.plateauSize = size;
        this.activeRovers = new ArrayList<>();
    }

    public List<Rover> getActiveRovers() {
        return activeRovers;
    }

    public void addRover(Rover rover) {
        if (rover == null) {
            throw new IllegalArgumentException("Rover cannot be null.");
        }

        activeRovers.add(rover);
    }

    public String size() {
        return plateauSize.formatSize();
    }

    public boolean isPositionEmpty(Position position) {
        if (position == null) {
            throw new IllegalArgumentException("Position cannot be null.");
        }

        return activeRovers.stream()
                .noneMatch(rover -> rover.hasPosition(position));
    }

    public boolean isPositionInBounds(Position position) {
        if (position == null) {
            throw new IllegalArgumentException("Position cannot be null.");
        }

        int x = position.getX();
        int y = position.getY();

        return x >= 0 && x < plateauSize.getX() && y >= 0 && y < plateauSize.getY();
    }
}
