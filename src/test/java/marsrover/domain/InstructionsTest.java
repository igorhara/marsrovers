package marsrover.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

import static marsrover.domain.DirectionEnum.*;
import static marsrover.domain.InstructionsEnum.*;
import static org.junit.Assert.assertEquals;

/**
 * This test will test the {@link InstructionsEnum} and if the Rover ends in the expected status.
 */
@RunWith(Parameterized.class)
public class InstructionsTest {

    @Parameter(0)
    public int x;
    @Parameter(1)
    public int y;
    @Parameter(2)
    public DirectionEnum direction;
    @Parameter(3)
    public InstructionsEnum instruction;
    @Parameter(4)
    public int expectedX;
    @Parameter(5)
    public int expectedY;
    @Parameter(6)
    public DirectionEnum expectedDirection;

    @Parameters(name = "Rover{index} {0} {1} {2} -> Instruction {3} = Rover{index} {4} {5} {6} ")
    public static Collection<Object[]> getScenarios() {
        return Arrays.asList(new Object[][]{
                //Turning Left scenarios
                {3, 3, N, L, 3, 3, W},
                {1, 1, E, L, 1, 1, N},
                {3, 3, S, L, 3, 3, E},
                {3, 3, W, L, 3, 3, S},
                //Turning Right scenarios
                {3, 3, N, R, 3, 3, E},
                {1, 1, E, R, 1, 1, S},
                {3, 3, S, R, 3, 3, W},
                {3, 3, W, R, 3, 3, N},
                //Moving scenarios
                {3, 3, N, M, 3, 4, N},
                {3, 3, S, M, 3, 2, S},
                {3, 3, W, M, 2, 3, W},
                {3, 3, E, M, 4, 3, E},
                //Edge cases for moving
                {0, 0, W, M, 0, 0, W},
                {0, 0, S, M, 0, 0, S},

        });
    }

    @Test
    public void applyScenario() {
        Plateau plateau = new Plateau(4,4);
        Rover rover = new Rover("Dummy", x, y, direction,plateau);
        instruction.executeInstruction(rover);
        rover.applyNewState();

        assertEquals("Unexpected X value.", expectedX, rover.getX());
        assertEquals("Unexpected Y value.", expectedY, rover.getY());
        assertEquals("Unexpected direction.", expectedDirection, rover.getDirection());
    }

}