package marsrover.parser;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DimensionParserTest {
    @Test
    @DisplayName("Should parse valid positive numbers string array into integer array")
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
    @DisplayName("Should throw exception when given invalid input")
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
}
