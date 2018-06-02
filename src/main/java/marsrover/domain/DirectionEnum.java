package marsrover.domain;

import java.util.Arrays;

/**
 * Definition of the directions in the plateau. Each direction has 2 sides to turn.
 * left and right methods that returns the new direction based on the current direction.
 * xChange and yChange will apply the change of the current direction to the X and Y values.
 */
public enum DirectionEnum {
    N("W","E",0,1),
    E("N","S",1,0),
    S("E","W",0,-1),
    W("S","N",-1,0);

    private DirectionEnum(String left, String right,int x, int y){
        this.left = left;
        this.right = right;
        this.xChange = x;
        this.yChange = y;
    }

    private String left;
    private String right;

    private int xChange;
    private int yChange;


    public DirectionEnum getLeft() {
        return DirectionEnum.valueOf(this.left);
    }

    public DirectionEnum getRight() {
        return DirectionEnum.valueOf(this.right);
    }

    public int XChange(){
        return this.xChange;
    }

    public int YChange(){
        return this.yChange;
    }
    public static String getEnumAsString(){
        return Arrays.stream(DirectionEnum.values()).map(DirectionEnum::toString).reduce("",
                (total,s)->total+s);
    }
}
