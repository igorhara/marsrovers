package marsrover.executor;

import marsrover.domain.InstructionsEnum;
import marsrover.domain.Rover;

import java.util.Arrays;

/**
 * Responsible for translation a String with instructions into {@link InstructionsEnum}
 * and then executing them in the {@link Rover} passed by parameter
 * Automatically applies the new State in the rover calling {@link Rover#applyNewState}
 */
public class InstructionExecutor {

    public void executeInstructions(Rover rover, String instructions){
        for (char c : instructions.toCharArray()) {
            InstructionsEnum.valueOf(String.valueOf(c)).executeInstruction(rover);
            rover.applyNewState();
        }
    }
}
