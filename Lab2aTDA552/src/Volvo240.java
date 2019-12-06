import java.awt.*;
/**
 * Represents a specific car model
 */
public class Volvo240 extends Car{

    private final static double trimFactor = 1.25;

    /**
     * Constructs a Volvo240
     */
    public Volvo240(Color color){
        super(color, "Volvo240", 100, 1400,4);
    }

    /**
     * Access the the speed factor based on engine power and trim factor
     * @return the speed factor
     */
    protected double speedFactor(){
        return getEnginePower() * 0.01 * trimFactor;
    }
}
