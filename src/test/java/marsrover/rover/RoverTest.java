package marsrover.rover;

import static org.junit.jupiter.api.Assertions.*;

import marsrover.command.Command;
import marsrover.direction.Direction;
import marsrover.exceptions.PositionOutOfBoundsException;
import marsrover.exceptions.RoverAlreadyLandedException;
import marsrover.exceptions.RoverCollisionException;
import marsrover.plateau.Plateau;
import marsrover.plateau.PlateauSize;
import marsrover.position.Position;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.List;

public class RoverTest {
    @Test
    @DisplayName("Rover: should throw exception when creating rover with invalid inputs")
    void testRoverCreation() {
        assertAll("Grouped Assertion for creating Rover with invalid input",
                () -> assertThrows(IllegalArgumentException.class, () -> new Rover("")),
                () -> assertThrows(IllegalArgumentException.class, () -> new Rover(null))
                );
    }

    @Test
    @DisplayName("landOnPlateau: should be able to land on plateau with given position")
    void testLandOnPlateau() {
        var plateau = new Plateau(new PlateauSize(5, 5));

        var position1 = new Position(1, 5, Direction.N);
        var position2 = new Position(2, 5, Direction.S);
        var position3 = new Position(3, 5, Direction.E);

        var rover1 = new Rover("mk1");
        var rover2 = new Rover("mk1");
        var rover3 = new Rover("mk1");

        rover1.landOnPlateau(plateau, position1);
        rover2.landOnPlateau(plateau, position2);
        rover3.landOnPlateau(plateau, position3);

        var result1 = rover1.reportPosition();
        var result2 = rover2.reportPosition();
        var result3 = rover3.reportPosition();

        var expectedResult1 = "1 5 N";
        var expectedResult2 = "2 5 S";
        var expectedResult3 = "3 5 E";

        assertAll("Grouped Assertions for landOnPlateau",
                () -> assertEquals(expectedResult1, result1),
                () -> assertEquals(expectedResult2, result2),
                () -> assertEquals(expectedResult3, result3)
        );
    }

    @Test
    @DisplayName("landOnPlateau: should not be able to land if it's already landed")
    void testLandOnPlateauWhenLanded() {
        var plateau = new Plateau(new PlateauSize(5, 5));

        var position = new Position(1, 5, Direction.N);

        var rover = new Rover("mk1");

        rover.landOnPlateau(plateau, position);

        assertThrows(RoverAlreadyLandedException.class, () -> rover.landOnPlateau(plateau, position));
    }

    @Test
    @DisplayName("executeCommands: should move rover correctly when given valid list of commands")
    void testExecuteCommands() {
        var plateau1 = new Plateau(new PlateauSize(5, 5));
        var plateau2 = new Plateau(new PlateauSize(5, 5));

        var position1 = new Position(1, 5, Direction.N);
        var position2 = new Position(2, 5, Direction.S);
        var position3 = new Position(3, 5, Direction.E);

        var rover1 = new Rover("mk1");
        var rover2 = new Rover("mk1");
        var rover3 = new Rover("mk1");

        rover1.landOnPlateau(plateau1, position1);
        rover2.landOnPlateau(plateau2, position2);
        rover3.landOnPlateau(plateau2, position3);

        var result1 = rover1.executeCommands(List.of(Command.R, Command.M));
        var result2 = rover2.executeCommands(List.of(Command.M, Command.L, Command.M));
        var result3 = rover3.executeCommands(List.of(Command.M, Command.R, Command.M, Command.M));

        var expectedResult1 = "2 5 E";
        var expectedResult2 = "3 4 E";
        var expectedResult3 = "4 3 S";

        assertAll("Grouped Assertions for executeCommands",
                () -> assertEquals(expectedResult1, result1),
                () -> assertEquals(expectedResult2, result2),
                () -> assertEquals(expectedResult3, result3)
        );
    }

    @Test
    @DisplayName("executeCommands: should return PositionOutOfBoundsException when trying to move rover out of Plateau")
    void testExecuteCommandsWhenMovingOutOfBounds() {
        var plateau = new Plateau(new PlateauSize(5, 5));

        var position1 = new Position(1, 5, Direction.N);
        var position2 = new Position(0, 0, Direction.S);
        var position3 = new Position(1, 5, Direction.N);

        var rover1 = new Rover("mk1");
        var rover2 = new Rover("mk2");
        var rover3 = new Rover("mk3");

        rover1.landOnPlateau(plateau, position1);
        rover2.landOnPlateau(plateau, position2);
        rover3.landOnPlateau(plateau, position3);

        assertAll("Grouped Assertions for executeCommands when attempting to move out of plateau bounds",
                () -> assertThrows(PositionOutOfBoundsException.class, () -> rover1.executeCommands(List.of(Command.M))),
                () -> assertThrows(PositionOutOfBoundsException.class, () -> rover2.executeCommands(List.of(Command.M))),
                () -> assertThrows(PositionOutOfBoundsException.class, () -> rover3.executeCommands(List.of(Command.L, Command.M, Command.M)))
                );
    }

    @Test
    @DisplayName("executeCommands: should return RoverCollisionException when trying to move rover into an occupied position")
    void testExecuteCommandsWhenMovingIntoAnotherRover() {
        var plateau = new Plateau(new PlateauSize(5, 5));

        var position1 = new Position(1, 5, Direction.E);
        var position2 = new Position(2, 5, Direction.W);
        var position3 = new Position(3, 4, Direction.N);

        var rover1 = new Rover("mk1");
        var rover2 = new Rover("mk2");
        var rover3 = new Rover("mk3");

        rover1.landOnPlateau(plateau, position1);
        rover2.landOnPlateau(plateau, position2);
        rover3.landOnPlateau(plateau, position3);

        assertAll("Grouped Assertions for executeCommands when attempting to move into an occupied space",
                () -> assertThrows(RoverCollisionException.class, () -> rover1.executeCommands(List.of(Command.M))),
                () -> assertThrows(RoverCollisionException.class, () -> rover2.executeCommands(List.of(Command.M))),
                () -> assertThrows(RoverCollisionException.class, () -> rover3.executeCommands(List.of(Command.M, Command.L, Command.M))
        ));
    }

    @Test
    @DisplayName("hasPosition: should return true/false when given valid position")
    void testHasPositionWithValidInputs() {
        var plateau = new Plateau(new PlateauSize(5, 5));

        var position1 = new Position(1, 5, Direction.N);
        var position2 = new Position(2, 5, Direction.S);
        var position3 = new Position(1, 5, Direction.E);

        var rover = new Rover("mk1");

        rover.landOnPlateau(plateau, position1);

        assertAll("Grouped Assertion for hasPosition with valid input",
                () -> assertTrue(rover.hasPosition(position1)),
                () -> assertFalse(rover.hasPosition(position2)),
                () -> assertTrue(rover.hasPosition(position3))
        );
    }

    @Test
    @DisplayName("hasPosition: should throw exception when creating rover with invalid inputs")
    void testHasPositionWithInvalidInputs() {
        var rover = new Rover("mk1");

        assertThrows(IllegalArgumentException.class, () -> rover.hasPosition(null));
    }
}
