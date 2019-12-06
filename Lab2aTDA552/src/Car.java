import java.awt.*;
import java.util.Vector;

/**
 * Represents a Car with a certain number of doors, model name, color etc.
 */
abstract public class Car implements Movable{
    private String modelName;
    private double enginePower;
    private int nrDoors;
    private Color color;
    private double currentSpeed;
    private double direction;
    private double x;
    private double y;
    private double weight;


    /**
     * Constructs a car
     */
    public Car(Color color, String modelName, double enginePower,double weight, int nrDoors){
        this.color = color;
        this.modelName = modelName;
        this.enginePower = enginePower;
        this.weight = weight;
        this.nrDoors = nrDoors;
        x = 0;
        y = 0;
        direction = 0;
        stopEngine();
    }

    /**
     * Changes the location based on current direction
     */
    @Override
    public void move() {
        x += (Math.cos(direction)) * getCurrentSpeed();
        y -= Math.sin(direction) * getCurrentSpeed();
    }

    /**
     * Increases the value of direction
     * @param amount difference in direction
     */
    @Override
    public void turnLeft(double amount) {
        //increase direction by amount
        direction = (direction + amount) % (Math.PI*2);
    }

    /**
     * Decreases the value of direction
     * @param amount difference in direction
     */
    @Override
    public void turnRight(double amount) {
        //decrease direction by amount
        direction = ((Math.PI*2) + (direction - amount)) % (Math.PI*2);
    }

    /**
     * while in transport, set car's position to truck's position
     * @param towTruck truck to campare with
     */
    public void setPosInTransport(TowTruck towTruck){
        if (towTruck.getStoredCars().contains(this)){
            x = towTruck.getX();
            y = towTruck.getY();
        }
    }

    public void setPosAfterTransport(TowTruck towTruck){
        if (towTruck.getStoredCars().contains(this)){
            x = towTruck.getX() + 10;
            y = towTruck.getY() + 10;
        }
    }

    /**
     * To access the direction
     * @return the current value of direction in radians
     */
    public double getDirection() {
        return direction;
    }

    /**
     * To access the weight
     * @return the current value of weight
     */
    public double getWeight(){
        return weight;
    }

    /**
     * To access the x-coordinate
     * @return the current value of x
     */
    public double getX(){
        return x;
    }

    /**
     * To access the y-coordinate
     * @return the current value of y
     */
    public double getY(){
        return y;
    }

    /**
     * To access the number of doors
     * @return the current number of doors
     */
    public int getNrDoors(){
        return nrDoors;
    }

    /**
     * To access the engine power
     * @return the current engine power
     */
    public double getEnginePower(){
        return enginePower;
    }

    /**
     * To access the current speed
     * @return the current speed
     */
    public double getCurrentSpeed(){
        return currentSpeed;
    }

    /**
     * To access the color
     * @return the current color
     */
    public Color getColor(){
        return color;
    }

    /**
     * Changes the color
     * @param clr the color of which current color is changed to
     */
    public void setColor(Color clr){
        color = clr;
    }

    /**
     * Sets current speed to 0.1
     */
    public void startEngine(){
        currentSpeed = 0.1;
    }

    /**
     * Sets current speed to 0
     */
    public void stopEngine(){
        currentSpeed = 0;
    }

    /**
     * To access the speed factor
     * @return the value of speed factor
     */
    abstract protected double speedFactor();

    /**
     * Increases the value of speed
     * @param amount amount of which the speed is increased
     */
    private void incrementSpeed(double amount){
        currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount, enginePower);
    }

    /**
     * Decreases the value of speed
     * @param amount amount of which the speed is decreased
     */
    private void decrementSpeed(double amount){
        currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount, 0);
    }

    /**
     * Increases the speed depending on amount
     * @param amount pressure put on the gas pedal
     */
    public void gas(double amount){
        if (amount >= 0 && amount <= 1){
            incrementSpeed(amount);
        }
    }

    /**
     * Decreases the speed depending on amount
     * @param amount pressure put on the break pedal
     */
    public void brake(double amount){
        if (amount >= 0 && amount <= 1){
            decrementSpeed(amount);
        }
    }
}
