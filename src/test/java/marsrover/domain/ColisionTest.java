package marsrover.domain;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by igorhara on 01/06/2018.
 */
public class ColisionTest {


    @Test
    public void checkColision_twoRovers_shouldColide(){
        Plateau plateau = new Plateau(4,4);
        Rover rover = new Rover("Dummy", 2, 2, DirectionEnum.N,plateau);
        Rover rover2 = new Rover("Dummy", 2, 1, DirectionEnum.N,plateau);
        InstructionsEnum.M.executeInstruction(rover2);
        boolean result = rover2.applyNewState();
        Assert.assertFalse(result);
        Assert.assertEquals(rover2.getX(),2);
        Assert.assertEquals(rover2.getY(),1);

    }

    @Test
    public void checkColision_twoRovers_shouldNotColide(){
        Plateau plateau = new Plateau(4,4);
        Rover rover = new Rover("Dummy", 2, 2, DirectionEnum.N,plateau);
        Rover rover2 = new Rover("Dummy", 2, 1, DirectionEnum.E,plateau);
        InstructionsEnum.M.executeInstruction(rover2);
        boolean result = rover2.applyNewState();
        Assert.assertTrue(result);
        Assert.assertEquals(rover2.getX(),3);
        Assert.assertEquals(rover2.getY(),1);

    }
}
