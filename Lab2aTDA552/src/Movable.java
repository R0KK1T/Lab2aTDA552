/**
 * Represents an object of changeable location
 */
public interface Movable{

    /**
     * Moves the object
     */
    void move();

    /**
     * Rotates the object counter clockwise
     */
    void turnLeft(double amount);

    /**
     * Rotates the object clockwise
     */
    void turnRight(double amount);
}
