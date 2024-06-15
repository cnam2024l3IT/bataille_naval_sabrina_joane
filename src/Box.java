import java.io.Serializable;

public class Box  {

    private boolean isOccupied;
    private boolean isHit;
    private Ship ship;

    public Box() {
        this.isOccupied = false;
        this.isHit = false;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    public boolean isHit() {
        return isHit;
    }

    public void setHit(boolean hit) {
        this.isHit = hit;
    }


    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }
}