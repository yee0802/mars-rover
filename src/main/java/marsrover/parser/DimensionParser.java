package marsrover.parser;

public class DimensionParser extends Parser<int[]> {
    @Override
    public int[] parse(String input) {
        String[] inputArr = InputValidator.validate(input).split(" ");

        if (inputArr.length != 2) {
            throw new IllegalArgumentException("Input does not match the required format 'number space number'.");
        }

        try {
            int x = Integer.parseInt(inputArr[0]);
            int y = Integer.parseInt(inputArr[1]);

            return new int[]{x, y};
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Input contains non-integer values.");
        }
    }

    public static int[] parseDimensions(String input) {
        return new DimensionParser().parse(input);
    }
}
