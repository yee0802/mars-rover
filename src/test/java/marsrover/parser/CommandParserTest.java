package marsrover.parser;

import marsrover.command.Command;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CommandParserTest {
    @Test
    @DisplayName("Should parse valid input into correct format")
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

    @DisplayName("Should throw exception when given invalid input")
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
