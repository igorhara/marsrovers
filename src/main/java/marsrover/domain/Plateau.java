package marsrover.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * represents the plateau for checking the max X and max Y
 */
public class Plateau {

    int XSize;
    int YSize;

    List<Rover> rovers = new ArrayList<>();

    public Plateau(int XSize, int YSize) {
        this.XSize = XSize;
        this.YSize = YSize;
    }

    public int getXSize() {
        return XSize;
    }

    public int getYSize() {
        return YSize;
    }

    public void addRover(Rover rover){
        this.rovers.add(rover);
    }

    public boolean isRoverInSamePosition(int x, int y){
        return this.rovers.stream().anyMatch(r->r.getX()==x && r.getY()==y);
    }
}
