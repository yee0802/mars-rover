# Mars Rover

## Description:
This is my implementation of the Mars Rover kata in Java, built using a Test-Driven Development (TDD) approach.
The project simulates a rover landing on a plateau, navigating based on commands, and adhering to specified
boundaries and rules.

## Prerequisites
Before running the project, ensure you have the following installed:
- **Java Development Kit (JDK) 8+**
- **An IDE** (e.g. Intellij IDEA, Eclipse, or VS code) or a terminal with `javac`/`java`

## Running the Application
1. **Fork and Clone the Repository:**
```bash
git clone <repository_url> && cd mars-rover
```
2. **Run the application:**
 - Open the project in your IDE of choice.
 - Navigate to the `Main` class:\
   `src/main/java/marsrover/Main.java`
 - Run the program from your IDE or terminal.
3. **Input instructions:**
 - When prompted, provide:
   1. Number of Rovers to launch: (e.g. `2`).
   2. Name of the Rover: (e.g. `MRv1.0`).
   3. Plateau dimensions: `x y` (e.g. `5 5`)
   4. Landing position: `x y DIRECTION` (e.g. `1 3 E`)
   5. Movement commands: `L`, `R`, `M` (e.g. `LMLMRMM`)

## Example Input/Output
### Input:
```text
Enter number of Rovers to launch: 1
Enter rover name: MRv01
Enter plateau dimensions: (x y) 5 5
Enter plateau position: (x y direction) 1 2 N
Enter movement instructions for MRv01: (LRM...) LMLMRRRMMMM
```

### Output:
```text
Commencing landing sequence...
Dropping on plateau with size (6 6)...
Success!
Rover 'MRv01' is located at (1 2 N)

Rover has moved to position: 4 1 E
```

## Testing
This project includes a detailed test suite to ensure the proper functionality of all components.

### Running Tests
1. Open the project in your IDE.
2. Navigate to the test folder:\
`src/test/java`
3. Run the test suite to validate all functionality.

## Credits
Created by Kye Yee | 2024