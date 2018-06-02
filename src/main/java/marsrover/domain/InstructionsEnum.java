package marsrover.domain;

import java.util.Arrays;

/**
 * Created by igorhara on 08/05/2018.
 */
public enum InstructionsEnum {

    L(r-> r.setNewDirection(r.getDirection().getLeft())),
    R(r-> r.setNewDirection(r.getDirection().getRight())),
    M(r->{
        DirectionEnum d = r.getDirection();
        r.setXChange(d.XChange());
        r.setYChange(d.YChange());
    });


    private Instruction instruction;

    private InstructionsEnum(Instruction inst){
        this.instruction = inst;
    }

    public void executeInstruction(Rover rover){
        this.instruction.execute(rover);
    }

    public static String getEnumAsString(){
        return Arrays.stream(InstructionsEnum.values()).map(InstructionsEnum::toString).reduce("",
                (total,s)->total+s);
    }


}
