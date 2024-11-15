package marsrover.position;

import static org.junit.jupiter.api.Assertions.*;

import marsrover.direction.Direction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

public class PositionTest {
    @Test
    @DisplayName("Position: should throw exception if given invalid inputs")
    void testPosition() {
        assertAll("Grouped Assertions for creating Position class with invalid inputs",
                () -> assertThrows(IllegalArgumentException.class, () -> new Position(0, 0, null)),
                () ->assertThrows(IllegalArgumentException.class, () -> new Position(6, 6, null)),
                () -> assertThrows(IllegalArgumentException.class, () -> new Position(-1, -2, Direction.S)),
                () -> assertThrows(IllegalArgumentException.class, () -> new Position(-1, 2, Direction.W))
                );
    }

    @Test
    @DisplayName("formatCoordinates: should format coordinates correctly")
    void testFormatCoordinates() {
        var position1 = new Position(1, 1, Direction.N);
        var position2 = new Position(5, 5, Direction.E);
        var position3 = new Position(0, 0, Direction.S);

        var result1 = position1.formatCoordinates();
        var result2 = position2.formatCoordinates();
        var result3 = position3.formatCoordinates();

        var expectedResult1 = "1 1";
        var expectedResult2 = "5 5";
        var expectedResult3 = "0 0";

        assertAll("Grouped Assertions for formatCoordinates",
                () -> assertEquals(expectedResult1, result1),
                () -> assertEquals(expectedResult2, result2),
                () -> assertEquals(expectedResult3, result3)
        );
    }

    @Test
    @DisplayName("formatPosition: should format position correctly")
    void testFormatPosition() {
        var position1 = new Position(1, 1, Direction.N);
        var position2 = new Position(5, 5, Direction.E);
        var position3 = new Position(0, 0, Direction.S);

        var result1 = position1.formatPosition();
        var result2 = position2.formatPosition();
        var result3 = position3.formatPosition();

        var expectedResult1 = "1 1 N";
        var expectedResult2 = "5 5 E";
        var expectedResult3 = "0 0 S";

        assertAll("Grouped Assertions for formatPosition",
                () -> assertEquals(expectedResult1, result1),
                () -> assertEquals(expectedResult2, result2),
                () -> assertEquals(expectedResult3, result3)
        );
    }

    @Test
    @DisplayName("calculateNextPosition: should return next position based on given position's direction")
    void testCalculateNextPosition() {
        var result1 = new Position(1, 1, Direction.N).calculateNextPosition().formatPosition();
        var result2 = new Position(5, 5, Direction.E).calculateNextPosition().formatPosition();
        var result3 = new Position(0, 1, Direction.S).calculateNextPosition().formatPosition();

        var expectedResult1 = new Position(1, 2, Direction.N).formatPosition();
        var expectedResult2 = new Position(6, 5, Direction.E).formatPosition();
        var expectedResult3 = new Position(0, 0, Direction.S).formatPosition();

        assertAll("Grouped Assertions for calculateNextPosition",
                () -> assertEquals(expectedResult1, result1),
                () -> assertEquals(expectedResult2, result2),
                () -> assertEquals(expectedResult3, result3)
        );
    }

    @Test
    @DisplayName("moveForward: should move position by 1 based on position's direction")
    void testMoveForward() {
        var position1 = new Position(1, 1, Direction.N);
        var position2 = new Position(5, 5, Direction.E);
        var position3 = new Position(0, 0, Direction.S);
        var position4 = new Position(2, 5, Direction.W);

        position1.moveForward();
        position2.moveForward();
        position3.moveForward();
        position4.moveForward();

        var result1 = position1.formatPosition();
        var result2 = position2.formatPosition();
        var result3 = position3.formatPosition();
        var result4 = position4.formatPosition();

        var expectedResult1 = "1 2 N";
        var expectedResult2 = "6 5 E";
        var expectedResult3 = "0 -1 S";
        var expectedResult4 = "1 5 W";

        assertAll("Grouped Assertions for moveForward",
                () -> assertEquals(expectedResult1, result1),
                () -> assertEquals(expectedResult2, result2),
                () -> assertEquals(expectedResult3, result3),
                () -> assertEquals(expectedResult4, result4)
        );
    }
}
