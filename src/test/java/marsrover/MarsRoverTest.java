package marsrover;

import marsrover.command.Command;
import marsrover.direction.Direction;
import marsrover.exceptions.*;
import marsrover.plateau.Plateau;
import marsrover.plateau.PlateauSize;
import marsrover.position.Position;
import marsrover.rover.Rover;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MarsRoverTest {
    @Test
    @DisplayName("Rover should land on a plateau at a designated position and navigate using commands.")
    void testMarsRover() {
        Plateau plateau = new Plateau(new PlateauSize(5, 5));

        assertEquals("6 6", plateau.size());

        Rover rover = new Rover("MR-PROTOTYPE");
        Position initialPosition = new Position(1, 2, Direction.N);
        rover.landOnPlateau(plateau, initialPosition);

        assertEquals("1 2 N", rover.reportPosition());

        List<Command> commands = List.of(Command.L, Command.M, Command.L, Command.M, Command.L, Command.M, Command.L, Command.M, Command.M);

        rover.executeCommands(commands);

        assertEquals("1 3 N", rover.reportPosition());
    }

    @Test
    @DisplayName("Rover should throw exception when attempting to go out of bounds")
    void testMarsRoverOutOfBounds() {
        Plateau plateau = new Plateau(new PlateauSize(5, 5));

        Rover rover = new Rover("MR-PROTOTYPE");
        Position initialPosition = new Position(5, 5, Direction.S);
        rover.landOnPlateau(plateau, initialPosition);

        assertEquals("5 5 S", rover.reportPosition());

        List<Command> commands = List.of(Command.L, Command.M);

        assertThrows(PositionOutOfBoundsException.class, () -> rover.executeCommands(commands));
    }

    @Test
    @DisplayName("Rover should throw exception when there is a collision")
    void testMarsRoverCollision() {
        Rover rover1 = new Rover("MR-PROTOTYPE");
        Rover rover2 = new Rover("MR-PROTOTYPEv2");
        Rover rover3 = new Rover("MR-CRSHDUMY");

        Plateau plateau = new Plateau(new PlateauSize(5, 5));
        Position position1 = new Position(5, 5, Direction.S);
        Position position2 = new Position(5, 3, Direction.N);

        rover1.landOnPlateau(plateau, position1);
        rover2.landOnPlateau(plateau, position2);

        List<Command> commands = List.of(Command.M, Command.M);

        assertThrows(RoverCollisionException.class, () -> rover1.executeCommands(commands));
        assertThrows(RoverCollisionException.class, () -> rover2.executeCommands(commands));
        assertThrows(RoverCollisionException.class, () -> rover3.landOnPlateau(plateau, position1));
    }

    @Test
    @DisplayName("Rover should throw exception when trying to land again")
    void testMarsRoverLanding() {
        Plateau plateau = new Plateau(new PlateauSize(5, 5));

        Position position = new Position(1, 5, Direction.N);

        Rover rover = new Rover("MR-PROTOTYPE");

        rover.landOnPlateau(plateau, position);

        assertThrows(RoverAlreadyLandedException.class, () -> rover.landOnPlateau(plateau, position));
    }
}
