package marsrover.parser;

import marsrover.direction.Direction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PositionParserTest {
    @Test
    @DisplayName("Should parse valid input into correct format")
    void testParsePositions() {
        var input1 = "0 1 E";
        var input2 = "4 2 N";
        var input3 = "5 5 S";
        var input4 = "10 20 W";
        var input5 = "  13 101 n  ";

        var result1 = PositionParser.parsePositions(input1);
        var result2 = PositionParser.parsePositions(input2);
        var result3 = PositionParser.parsePositions(input3);
        var result4 = PositionParser.parsePositions(input4);
        var result5 = PositionParser.parsePositions(input5);

        var expectedResult1 = new Object[]{0, 1, Direction.E};
        var expectedResult2 = new Object[]{4, 2, Direction.N};
        var expectedResult3 = new Object[]{5, 5, Direction.S};
        var expectedResult4 = new Object[]{10, 20, Direction.W};
        var expectedResult5 = new Object[]{13, 101, Direction.N};

        assertAll("Grouped Assertions for parsePositions with valid input: ",
                () -> assertArrayEquals(expectedResult1, result1),
                () -> assertArrayEquals(expectedResult2, result2),
                () -> assertArrayEquals(expectedResult3, result3),
                () -> assertArrayEquals(expectedResult4, result4),
                () -> assertArrayEquals(expectedResult5, result5)
        );
    }

    @DisplayName("Should throw exception when given invalid input")
    @Test
    void testParsePositionsWithInvalidInput() {
        var input1 = "";
        var input2 = "4  2 N";
        var input3 = "55n";
        var input4 = "1 2 X";
        var input5 = "PARSE ME :D";

        assertAll("Grouped assertions for parsePositions with invalid input:",
                () -> assertThrows(IllegalArgumentException.class, () -> PositionParser.parsePositions(null)),
                () -> assertThrows(IllegalArgumentException.class, () -> PositionParser.parsePositions(input1)),
                () -> assertThrows(IllegalArgumentException.class, () -> PositionParser.parsePositions(input2)),
                () -> assertThrows(IllegalArgumentException.class, () -> PositionParser.parsePositions(input3)),
                () -> assertThrows(IllegalArgumentException.class, () -> PositionParser.parsePositions(input4)),
                () -> assertThrows(IllegalArgumentException.class, () -> PositionParser.parsePositions(input5))
        );
    }
}
