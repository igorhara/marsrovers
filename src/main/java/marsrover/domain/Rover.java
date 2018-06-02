package marsrover.domain;

/**
 * Rover that has a name, x, y position and a {@link DirectionEnum} it is facing.
 * after submitting a rover to an {@link InstructionsEnum} you must call {@link #applyNewState()}
 * in order to update the rovers state.
 * you can call {@link #resetLastInstruction()} to cancel last {@link InstructionsEnum} it has been submitted.
 * The Rover will ignore a instruction that will make it move out of the Plateau.
 */
public class Rover {

    private String name;
    private int x;
    private int y;
    private DirectionEnum direction;

    private int xChange;
    private int yChange;
    private DirectionEnum newDirection;

    private Plateau plateau;

    public static Rover build(String name, String[] landing, Plateau plateau) {
        Integer x = Integer.valueOf(landing[0]);
        Integer y = Integer.valueOf(landing[1]);
        DirectionEnum d = DirectionEnum.valueOf(landing[2]);
        return new Rover(name, x, y, d, plateau);
    }

    Rover(String name, int x, int y, DirectionEnum direction, Plateau plateau) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.plateau = plateau;
        this.plateau.addRover(this);
    }

    public String getName() {
        return name;
    }

    public int getX() {
        return x;
    }


    public int getY() {
        return y;
    }


    public DirectionEnum getDirection() {
        return direction;
    }

    public int getXChange() {
        return xChange;
    }

    void setXChange(int xChange) {
        this.xChange = xChange;
    }

    void setYChange(int yChange) {
        this.yChange = yChange;
    }

    void setNewDirection(DirectionEnum newDirection) {
        this.newDirection = newDirection;
    }


    /**
     * applies the Instruction details in the state of the Rover
     *
     * @return true if All was applied,
     * false if any of the details makes the Rover move out of the Plateau.
     */
    public boolean applyNewState() {
        if (!isMovementValid()) {
            this.resetLastInstruction();
            return false;
        }

        this.y += this.yChange;
        this.x += this.xChange;
        if (this.newDirection != null) {
            this.direction = this.newDirection;
        }

        this.resetLastInstruction();

        return true;
    }

    /**
     * validates if the last instruction will make the Rover move out of the Plateau.
     *
     * @return true if the last instruction will make the Rover move out of the Plateau.
     */
    private boolean isMovementValid() {
        int newY = this.yChange + this.y;
        if (newY < 0 || newY > plateau.getYSize()) {
            return false;
        }

        int newX = this.xChange + this.x;
        if (newX < 0 || newX > plateau.getXSize()) {
            return false;
        }

        if(this.plateau.isRoverInSamePosition(newX,newY)){
            return false;
        }
        return true;
    }

    /**
     * resets last instruction passed to the rover preparing for a new instruction.
     */
    public void resetLastInstruction() {
        this.xChange = 0;
        this.yChange = 0;
        this.newDirection = null;
    }

    public boolean isRoverInsidePlateau(){
        return isMovementValid();
    }

}
