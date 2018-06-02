package marsrover.input;

import marsrover.domain.Plateau;
import marsrover.domain.Rover;
import marsrover.executor.InstructionExecutor;

import java.util.ArrayList;
import java.util.List;

/**
 * Main class. This is the entry point for the excution of the application.
 */
public class Main {


    public static final String LANDING = " Landing:";
    public static final String INSTRUCTIONS = " Instructions:";
    public static final String MARS_ROVERS_APPLICATION = "Mars Rovers Application\n";
    public static final String NUMBER_OF_ROVERS = "Number of rovers: ";
    public static final String PLATEAU = "Plateau:";
    private InputOutputControl control;
    private InstructionExecutor executor;

    public static void main(String[] args) {

        Main main = new Main();

        main.initializeControl(new InputOutputControl(System.in));
        main.initializeExecutor(new InstructionExecutor());
        main.executeProgram();

    }

    public void initializeControl(InputOutputControl control){
            this.control=control;
    }
    public void initializeExecutor(InstructionExecutor instructionExecutor){
        if(instructionExecutor==null){
            executor = new InstructionExecutor();
        }else{
           this.executor=instructionExecutor;
        }
    }

    void executeProgram() {

        control.writeToOutput(MARS_ROVERS_APPLICATION);
        control.writeToOutput(NUMBER_OF_ROVERS);
        Integer iterations = control.readInt();
        if( iterations<=0){
            return;
        }
        control.writeToOutput(PLATEAU);
        int[] plateauSize = control.readPlateau();

        Plateau plateau = new Plateau(plateauSize[0], plateauSize[1]);

        List<Rover> rovers = new ArrayList<>(iterations);

        for (int i = 1; i <= iterations; i++) {
            String name = "Rover"+i;
            control.writeToOutput(name+ LANDING);
            String[] landing = control.readLanding();
            Rover rover = Rover.build(name, landing,plateau);
            if(!rover.isRoverInsidePlateau()){
                control.writeToOutput(name+" is not inside plateau, ignoring Rover");
                continue;
            }
            control.writeToOutput(name+ INSTRUCTIONS);
            String instructions = control.readInstructions();
            executor.executeInstructions(rover,instructions);
            rovers.add(rover);
        }

        rovers.forEach(r->{
            control.writeToOutput(String.format("%s:%d %d %s\n",r.getName(),r.getX(),r.getY(),r.getDirection()));
        });
    }


}
