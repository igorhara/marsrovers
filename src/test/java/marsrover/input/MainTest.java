package marsrover.input;

import marsrover.executor.InstructionExecutor;
import org.hamcrest.core.StringContains;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * This test mocks the {@link InputOutputControl} to be able to read the results.
 * It uses multiples scenarios provided in the {@link #getScenarios()}
 */
@RunWith(Parameterized.class)
public class MainTest {


    int[] plateau;

    InputOutputControl control;

    @Parameter(0)
    public String landingStr;
    @Parameter(1)
    public String instructions;
    @Parameter(2)
    public String result;

    Main main;
    InstructionExecutor executor;

    String name;
    @Before
    public void setUp() {
        name = "Rover1";
        plateau = new int[]{5, 5};
        control = Mockito.mock(InputOutputControl.class);
        main = new Main();
        executor = new InstructionExecutor();
        main.initializeControl(control);
        main.initializeExecutor(executor);

    }


    @Test
    public void testScenario() {
        Mockito.when(control.readInt()).thenReturn(1);
        Mockito.when(control.readPlateau()).thenReturn(plateau);
        Mockito.when(control.readLanding()).thenReturn(landingStr.split(" "));
        Mockito.when(control.readInstructions()).thenReturn(instructions);
        main.executeProgram();

        ArgumentCaptor<String> messageCaptor = ArgumentCaptor.forClass(String.class);

        Mockito.verify(control,Mockito.atLeast(5)).writeToOutput(messageCaptor.capture());

        List<String> allValues = messageCaptor.getAllValues();


        String lastMessage = allValues.get(allValues.size() - 1);
        Assert.assertThat(lastMessage, StringContains.containsString(result));

    }

    @Parameterized.Parameters(name = "Landing:{0}\n Instructions:{1}\n result:{2}")
    public static Collection<String[]> getScenarios() {
        return Arrays.asList(new String[][]{
                {"1 2 N", "LMLMLMLMM", "1 3 N"},
                {"3 3 E", "MMRMMRMRRM", "5 1 E"},
                {"4 2 E", "MMRMRMMMMM", "0 1 W"},
                {"4 4 S", "MMMRMMMRMM", "1 3 N"},
                {"0 0 N", "MMMRMMLLMMM", "0 3 W"},

        });
    }


}