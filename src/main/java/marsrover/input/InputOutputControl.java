package marsrover.input;

import marsrover.domain.DirectionEnum;
import marsrover.domain.InstructionsEnum;

import java.io.InputStream;
import java.util.Scanner;

/**
 * Class that will intermediate reading from the user input and sending messages to the user.
 */
public class InputOutputControl {

    
    InputStream input;

    public InputOutputControl(InputStream input) {
        this.input = input;
    }

    public Integer readInt() {
        Scanner scanner = new Scanner(input);
        while (scanner.hasNext() && !scanner.hasNextInt()) {
            System.out.println(scanner.next());
        }
        return scanner.nextInt();
    }

    public int[] readPlateau() {
        String line = getStringFromLine("\\d+ \\d+");
        String[] split = line.split(" ");
        return new int[]{Integer.valueOf(split[0]), Integer.valueOf(split[1])};
    }

    private String getStringFromLine(String pattern) {
        Scanner scanner = new Scanner(input);
        String line = scanner.findInLine(pattern);
        while (line == null) {
            scanner.nextLine();
            line = scanner.findInLine(pattern);
        }
        return line;
    }

    public String[] readLanding() {
        String line = getStringFromLine(getPatternForLanding());
        return line.split(" ");
    }

    private String getPatternForLanding() {
        return "\\d+ \\d+ ["+ DirectionEnum.getEnumAsString()+"]";
    }

    public String readInstructions() {
        return getStringFromLine(getPatternForInstructions());
    }

    private String getPatternForInstructions() {
        return "["+ InstructionsEnum.getEnumAsString()+"]+";
    }

    /**
     * prints to the default output device the message.
     * this default implementation prints to System.out
     *
     * @param message
     */
    public void writeToOutput(String message) {
        System.out.print(message);
    }
}
