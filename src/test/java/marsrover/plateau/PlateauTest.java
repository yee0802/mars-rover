package marsrover.plateau;

import static org.junit.jupiter.api.Assertions.*;

import marsrover.direction.Direction;

import marsrover.position.Position;
import marsrover.rover.Rover;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.List;

public class PlateauTest {
    @Test
    @DisplayName("addRover: Should add Rover to activeRovers when given valid Rover")
    void testPlateauAddsRoversWithValidInput() {
        var plateauSize = new PlateauSize(5, 5);
        var plateau = new Plateau(plateauSize);

        var rover1 = new Rover("mk1");
        var rover2 = new Rover("mk2");

        plateau.addRover(rover1);
        plateau.addRover(rover2);

        var result = plateau.getActiveRovers();

        var expectedResult = List.of(rover1, rover2);
        assertEquals(expectedResult, result);
    }

    @Test
    @DisplayName("addRover: Should throw exception when given invalid inputs")
    void testPlateauAddsRoversWithInvalidInput() {
        var plateauSize = new PlateauSize(5, 5);
        var plateau = new Plateau(plateauSize);

        assertAll("Grouped Assertions for addRover with invalid inputs",
                () -> assertThrows(IllegalArgumentException.class, () -> plateau.addRover(null)),
                () -> assertThrows(IllegalArgumentException.class, () -> plateau.addRover(new Rover(null))
        ));
    }

    @Test
    @DisplayName("isPositionEmpty: Should return true/false when given valid inputs")
    void testPlateauIsPositionEmptyWithValidInputs() {
        var plateauSize = new PlateauSize(5, 5);
        var plateau = new Plateau(plateauSize);
        var emptyPlateau = new Plateau(plateauSize);

        var rover1 = new Rover("mk1");
        var rover2 = new Rover("mk2");

        var position1 = new Position(1, 1, Direction.N);
        var position2 = new Position(1, 1, Direction.E);
        var position3 = new Position(0, 0, Direction.N);

        rover1.landOnPlateau(plateau, position1);
        rover2.landOnPlateau(plateau, position3);

        assertAll("Grouped Assertions for isPositionEmpty with valid inputs",
                () -> assertTrue(emptyPlateau.isPositionEmpty(position1)),
                () -> assertFalse(plateau.isPositionEmpty(position1)),
                () -> assertFalse(plateau.isPositionEmpty(position2)),
                () -> assertFalse(plateau.isPositionEmpty(position3))
                );
    }

    @Test
    @DisplayName("isPositionEmpty: Should throw exception when given invalid inputs")
    void testPlateauIsPositionEmptyWithInvalidInputs() {
        var plateauSize = new PlateauSize(5, 5);
        var plateau = new Plateau(plateauSize);

        assertThrows(IllegalArgumentException.class, () -> plateau.isPositionEmpty(null));
    }

    @Test
    @DisplayName("isPositionInBounds: Should return true/false when given valid inputs")
    void testPlateauIsPositionInBoundsWithValidInputs() {
        var plateauSize = new PlateauSize(5, 5);
        var plateau = new Plateau(plateauSize);

        var position1 = new Position(1, 1, Direction.N).calculateNextPosition();
        var position2 = new Position(5, 5, Direction.E).calculateNextPosition();
        var position3 = new Position(0, 0, Direction.S).calculateNextPosition();;
        var position4 = new Position(4, 5, Direction.E).calculateNextPosition();

        assertAll("Grouped Assertions for isPositionInBounds with valid inputs",
                () -> assertTrue(plateau.isPositionInBounds(position1)),
                () -> assertFalse(plateau.isPositionInBounds(position2)),
                () -> assertFalse(plateau.isPositionInBounds(position3)),
                () -> assertTrue(plateau.isPositionInBounds(position4))
        );
    }

    @Test
    @DisplayName("isPositionInBounds: Should throw exception when given invalid inputs")
    void testPlateauIsPositionInBoundsWithInvalidInputs() {
        var plateauSize = new PlateauSize(5, 5);
        var plateau = new Plateau(plateauSize);

        assertThrows(IllegalArgumentException.class, () -> plateau.isPositionInBounds(null));
    }
}
