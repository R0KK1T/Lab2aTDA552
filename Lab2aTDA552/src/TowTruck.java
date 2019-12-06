import java.awt.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

/**
 * Represents a tow truck
 */
public class TowTruck extends Car implements Storage<Car>{
    private boolean rampUp;
    private int maxCars;
    private double maxWeight;
    private Deque<Car> storedCars = new ArrayDeque<>();
    private double distance;

    /**
     * Constructs a tow truck
     */
    public TowTruck(Color color, int maxCars) {
        super(color, "Mater", 1500, 10000,2);
        this.maxCars = maxCars;
        maxWeight = getWeight()/4;
        rampUp = true;
        distance = 20;
    }

    /**
     * Moves the tow truck and loaded cars
     */
    @Override
    public void move() {
        super.move();
        for (Car car : storedCars) {
            car.setPosInTransport(this);
        }
    }

    /**
     * To access the storedCars stack
     * @return the current stack of cars
     */
    public Deque<Car> getStoredCars() {
        return storedCars;
    }

    /**
     * opens the ramp
     */
    public void openRamp(){
        if (getCurrentSpeed() == 0){
            rampUp = false;
        }
    }

    /**
     * closes the ramp
     */
    public void closeRamp(){
        rampUp = true;
    }

    /**
     * loads a car onto the tow truck
     * @param car car to load
     */
    public void load(Car car){
        if (storedCars.size() < maxCars && !rampUp && inProximity(car) && !storedCars.contains(car)){
            storedCars.push(car);
        }
    }

    /**
     * unloads the utter most car from the tow truck
     * @param car car to unload
     */
    public void unload(Car car){
        Deque<Car> stack = new ArrayDeque<>();
        if (storedCars.contains(car)){
            while(storedCars.getFirst() != car && !rampUp){
                stack.push(storedCars.pop());
            }
            car.setPosAfterTransport(this);
            storedCars.pop();

            while(stack.size() > 0){
                storedCars.push(stack.pop());
            }
        }
    }

    /**
     * checks if distance to car is in range
     * @param car car to check
     * @return "is car close enough?"
     */
    private boolean inProximity(Car car){
        return getX() - car.getX() <= distance && getX() - car.getX() >= -distance
                && getY() - car.getY() <= distance && getY() - car.getY() >= -distance;
    }

    /**
     * Starts the engine
     */
    public void startEngine(){
        if (rampUp) {
            super.startEngine();
        }
    }

    /**
     * Access the the speed factor based on engine power
     * @return the speed factor
     */
    protected double speedFactor() {
        if (rampUp){
            return getEnginePower() * 0.01;
        }
        return 0;
    }
}
