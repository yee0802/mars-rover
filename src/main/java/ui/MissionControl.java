package ui;

import marsrover.command.Command;
import marsrover.direction.Direction;
import marsrover.parser.*;
import marsrover.plateau.*;
import marsrover.position.Position;
import marsrover.rover.Rover;

import java.util.List;
import java.util.Scanner;

public class MissionControl {
    public void launch() {
        Scanner scanner = new Scanner(System.in);

        int numOfRovers;
        while (true) {
            try {
                System.out.print("Enter number of Rovers to launch: ");
                String input = scanner.nextLine();
                numOfRovers = Integer.parseInt(input);
                break;
            } catch (Exception e) {
                printError(e);
                System.out.println("Please enter a valid integer.");
            }
        }

        while (numOfRovers > 0) {
            Rover rover;
            while (true) {
                try {
                    System.out.print("Enter rover name: ");
                    String name = scanner.nextLine();
                    rover = new Rover(name);
                    break;
                } catch (Exception e) {
                    printError(e);
                }
            }

            Plateau plateau;
            while (true) {
                try {
                    System.out.print("Enter plateau dimensions: (x y) ");
                    String dimensions = scanner.nextLine();
                    plateau = calculatePlateauPosition(dimensions);break;
                } catch (Exception e) {
                    printError(e);
                }
            }

            Position landingPosition;
            while (true) {
                try {
                    System.out.print("Enter plateau position: (x y direction) ");
                    String landingPositionString = scanner.nextLine();
                    landingPosition = calculatePosition(landingPositionString);
                    break;
                } catch (Exception e) {
                    printError(e);
                }
            }

            System.out.println("\nCommencing landing sequence...");
            System.out.printf("Dropping on plateau with size (%s)...\n", plateau.size());
            rover.landOnPlateau(plateau, landingPosition);
            System.out.printf("Success!\nRover '%s' is located at (%s)\n\n", rover.getName(), rover.reportPosition());

            List<Command> commandList;
            while (true) {
                try {
                    System.out.printf("Enter movement instructions for '%s': (LMRM...) ", rover.getName());
                    String commands = scanner.nextLine();
                    commandList = calculateMovement(commands);
                    rover.executeCommands(commandList);
                    break;
                } catch (Exception e) {
                    printError(e);
                }
            }

            System.out.printf("\nRover has moved to position: %s%n", rover.reportPosition());
            numOfRovers--;
        }
    }

    private static Plateau calculatePlateauPosition(String dimensionString) {
        int[] dimensions = DimensionParser.parseDimensions(dimensionString);

        PlateauSize plateauSize = new PlateauSize(dimensions[0], dimensions[1]);

        return new Plateau(plateauSize);
    }

    private static Position calculatePosition(String positionString) {
        var position = PositionParser.parsePositions(positionString);

        int x = (int) position[0];
        int y = (int) position[1];
        Direction direction = (Direction) position[2];

        return new Position(x, y, direction);
    }

    private static List<Command> calculateMovement(String commmandsString) {
        return CommandParser.parseCommands(commmandsString);
    }

    private static void printError(Exception e) {
        System.out.println("Error: " + e.getMessage());
    }
}
