package marsrover.parser;

public class InputValidator {
    public static String validate(String input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("Input is null or empty.");
        }

        return input.trim().toUpperCase();
    }
}
