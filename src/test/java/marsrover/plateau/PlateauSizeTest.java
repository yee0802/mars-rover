package marsrover.plateau;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlateauSizeTest {
    @Test
    @DisplayName("Should save correct PlateauSize when given valid coordinates")
    void testPlateauSizeWithValidInput() {
        var plateauSize1 = new PlateauSize(5, 5);
        var plateauSize2 = new PlateauSize(10, 10);
        var plateauSize3 = new PlateauSize(4, 12);
        var plateauSize4 = new PlateauSize(50, 49);

        var result1 = plateauSize1.formatSize();
        var result2 = plateauSize2.formatSize();
        var result3 = plateauSize3.formatSize();
        var result4 = plateauSize4.formatSize();

        var expectedResult1 = "6 6";
        var expectedResult2 = "11 11";
        var expectedResult3 = "5 13";
        var expectedResult4 = "51 50";

        assertAll("Grouped Assertions for creating PlateauSize with valid inputs",
                () -> assertEquals(expectedResult1, result1),
                () -> assertEquals(expectedResult2, result2),
                () -> assertEquals(expectedResult3, result3),
                () -> assertEquals(expectedResult4, result4)
        );
    }

    @Test
    @DisplayName("Should throw correct exception when given invalid coordinates")
    void testPlateauSizeWithInvalidInput() {
        assertAll("Grouped Assertions for creating PlateauSize with invalid inputs",
                () -> assertThrows(IllegalArgumentException.class, () -> new PlateauSize(0, 0)),
                () -> assertThrows(IllegalArgumentException.class, () -> new PlateauSize(0, 6)),
                () -> assertThrows(IllegalArgumentException.class, () -> new PlateauSize(-1, 5)),
                () -> assertThrows(IllegalArgumentException.class, () -> new PlateauSize(-5, -10))
        );
    }
}
