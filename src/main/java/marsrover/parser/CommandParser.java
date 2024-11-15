package marsrover.parser;

import marsrover.command.Command;

import java.util.ArrayList;
import java.util.List;

public class CommandParser extends Parser<List<Command>> {
    @Override
    public List<Command> parse(String input) {
        String[] inputArr = InputValidator.validate(input).split("");

        List<Command> commands = new ArrayList<>();

        try {
            for (String c : inputArr) {
                commands.add(Command.valueOf(c));
            }

            return commands;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Given input contains invalid commands. Please input 'L, R or M'.");
        }
    }

    public static List<Command> parseCommands(String input) {
        return new CommandParser().parse(input);
    }
}
