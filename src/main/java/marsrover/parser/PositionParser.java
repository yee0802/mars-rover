package marsrover.parser;

import marsrover.CompassDirection;

public class PositionParser extends Parser<Object[]> {
    @Override
    public Object[] parse(String input) {
        String[] inputArr = InputValidator.validate(input).split(" ");

        if (inputArr.length != 3) {
            throw new IllegalArgumentException("Input does not match the required format 'number space number space char'.");
        }

        try {
            int x = Integer.parseInt(inputArr[0]);
            int y = Integer.parseInt(inputArr[1]);
            CompassDirection direction = CompassDirection.valueOf(inputArr[2]);

            return new Object[]{x, y, direction};
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Input contains non-integer values.");
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Direction is not valid. Please input 'N, E, S or W'.");
        }
    }

    public static Object[] parsePositions(String input) {
        return new PositionParser().parse(input);
    }
}
