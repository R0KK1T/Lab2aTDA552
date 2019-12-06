import java.awt.*;

/**
 * Represents a specific car model
 */
public class Saab95 extends Car{

    private boolean turboOn;

    /**
     * Constructs a Saab95
     */
    public Saab95(Color color){
        super(color, "Saab95", 125, 1200,2);
	    turboOn = false;
    }

    /**
     * Sets the value of turboOn to true
     */
    public void setTurboOn(){
	    turboOn = true;
    }

    /**
     * Sets the value of turboOn to false
     */
    public void setTurboOff(){
	    turboOn = false;
    }

    /**
     * Access the the speed factor based on engine power and turbo
     * @return the speed factor
     */
    protected double speedFactor(){
        double turbo = 1;
        if(turboOn) turbo = 1.3;
        return getEnginePower() * 0.01 * turbo;
    }
}
