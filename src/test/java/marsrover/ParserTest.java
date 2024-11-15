package marsrover;

import static org.junit.jupiter.api.Assertions.*;

import marsrover.command.Command;
import marsrover.parser.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.List;

public class ParserTest {
    @Test
    @DisplayName("DimensionParser: Should parse valid positive numbers string array into integer array")
    void testParseDimensions() {
        var input1 = "0 1";
        var input2 = "4 2";
        var input3 = "5 5";
        var input4 = "10 20";
        var input5 = "  13 101  ";

        var result1 = DimensionParser.parseDimensions(input1);
        var result2 = DimensionParser.parseDimensions(input2);
        var result3 = DimensionParser.parseDimensions(input3);
        var result4 = DimensionParser.parseDimensions(input4);
        var result5 = DimensionParser.parseDimensions(input5);

        var expectedResult1 = new int[]{0, 1};
        var expectedResult2 = new int[]{4, 2};
        var expectedResult3 = new int[]{5, 5};
        var expectedResult4 = new int[]{10, 20};
        var expectedResult5 = new int[]{13, 101};

        assertAll("Grouped Assertions for parseDimensions with valid input: ",
                () -> assertArrayEquals(expectedResult1, result1),
                () -> assertArrayEquals(expectedResult2, result2),
                () -> assertArrayEquals(expectedResult3, result3),
                () -> assertArrayEquals(expectedResult4, result4),
                () -> assertArrayEquals(expectedResult5, result5)
                );
    }

    @Test
    @DisplayName("DimensionParser: Should throw error when given invalid input")
    void testParseDimensionsWithInvalidInput() {
        var input1 = "";
        var input2 = "4  2";
        var input3 = "555";
        var input4 = "12#'2#3ssa2";
        var input5 = "parse me :)";


        assertAll("Grouped Assertions for parseDimensions with invalid inputs: ",
                () -> assertThrows(IllegalArgumentException.class, () -> DimensionParser.parseDimensions(null)),
                () -> assertThrows(IllegalArgumentException.class, () -> DimensionParser.parseDimensions(input1)),
                () -> assertThrows(IllegalArgumentException.class, () -> DimensionParser.parseDimensions(input2)),
                () -> assertThrows(IllegalArgumentException.class, () -> DimensionParser.parseDimensions(input3)),
                () -> assertThrows(IllegalArgumentException.class, () -> DimensionParser.parseDimensions(input4)),
                () -> assertThrows(IllegalArgumentException.class, () -> DimensionParser.parseDimensions(input5))
        );
    }

    @Test
    @DisplayName("PositionParser: Should parse valid input into correct format")
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

        var expectedResult1 = new Object[]{0, 1, CompassDirection.E};
        var expectedResult2 = new Object[]{4, 2, CompassDirection.N};
        var expectedResult3 = new Object[]{5, 5, CompassDirection.S};
        var expectedResult4 = new Object[]{10, 20, CompassDirection.W};
        var expectedResult5 = new Object[]{13, 101, CompassDirection.N};

        assertAll("Grouped Assertions for parsePositions with valid input: ",
                () -> assertArrayEquals(expectedResult1, result1),
                () -> assertArrayEquals(expectedResult2, result2),
                () -> assertArrayEquals(expectedResult3, result3),
                () -> assertArrayEquals(expectedResult4, result4),
                () -> assertArrayEquals(expectedResult5, result5)
        );
    }

    @DisplayName("PositionParser: Should throw error when given invalid input")
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

    @Test
    @DisplayName("CommandParser: Should parse valid input into correct format")
    void testParseCommands() {
        var input1 = "LRM";
        var input2 = "LLLL";
        var input3 = "LRMLRMMMML";

        var result1 = CommandParser.parseCommands(input1);
        var result2 = CommandParser.parseCommands(input2);
        var result3 = CommandParser.parseCommands(input3);

        var expectedResult1 = List.of(Command.L, Command.R, Command.M);
        var expectedResult2 = List.of(Command.L, Command.L, Command.L, Command.L);
        var expectedResult3 = List.of(Command.L, Command.R, Command.M,Command.L, Command.R, Command.M, Command.M, Command.M, Command.M, Command.L);

        assertAll("Grouped Assertions for parseDimensions with positive integers: ",
                () -> assertEquals(expectedResult1, result1),
                () -> assertEquals(expectedResult2, result2),
                () -> assertEquals(expectedResult3, result3)
        );
    }

    @DisplayName("CommandParser: Should throw error when given invalid input")
    @Test
    void testParseCommandsWithInvalidInput() {
        var input1 = "";
        var input2 = "LRXM";
        var input3 = "1LM2..";
        var input4 = "LRL RL";
        var input5 = "L  0RM";

        assertAll("Grouped assertions for parseCommands with invalid input:",
                () -> assertThrows(IllegalArgumentException.class, () -> CommandParser.parseCommands(null)),
                () -> assertThrows(IllegalArgumentException.class, () -> CommandParser.parseCommands(input1)),
                () -> assertThrows(IllegalArgumentException.class, () -> CommandParser.parseCommands(input2)),
                () -> assertThrows(IllegalArgumentException.class, () -> CommandParser.parseCommands(input3)),
                () -> assertThrows(IllegalArgumentException.class, () -> CommandParser.parseCommands(input4)),
                () -> assertThrows(IllegalArgumentException.class, () -> CommandParser.parseCommands(input5))
        );
    }
}