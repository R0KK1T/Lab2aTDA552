import java.awt.*;

/**
 * Represents a specific car model
 */
public class Scania extends Car {
    private double  tippingAngle;
    private final double maxTippingAngle = 70; //Degrees

    /**
     * Constructs a Scania
     */
    public Scania(Color color) {
        super(color, "Scania", 750, 7000,2);
        tippingAngle = 0;
    }

    /**
     * To access the tipping angle
     * @return the current value of tippingAngle
     */
    public double getTippingAngle(){
        return tippingAngle;
    }

    /**
     * Increases the value of tippingAngle
     * @param amount amount of which the tipping angle is increased
     */
    public void increaseAngle(double amount){
        if (getCurrentSpeed() == 0){
            tippingAngle = Math.min(getTippingAngle() + amount, maxTippingAngle);
        }
    }

    /**
     * Decreases the value of tippingAngle
     * @param amount amount of which the tipping angle is decreased
     */
    public void decreaseAngle(double amount){
        tippingAngle = Math.max(getTippingAngle() - amount, 0);
    }

    /**
     * Starts the engine
     */
    public void startEngine(){
        if (getTippingAngle() == 0) {
            super.startEngine();
        }
    }

    /**
     * Access the the speed factor based on engine power
     * @return the speed factor
     */
    protected double speedFactor() {
        if (getTippingAngle() == 0){
            return getEnginePower() * 0.01;
        }
        return 0;
    }

}
