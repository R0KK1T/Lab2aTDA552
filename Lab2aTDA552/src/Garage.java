import java.util.ArrayList;
import java.util.List;

/**
 * Represents a car garage
 */
public class Garage <T extends Car> implements Storage<T>{
    private List<T> cars = new ArrayList<>();
    private int maxCars;
    private double x;
    private double y;
    private double distance;

    /**
     * Constructs a garage
     * @param maxCars Maximum amount of cars that the garage is able to store
     * @param x x-coordinate
     * @param y y-coordinate
     * @param distance Acceptable loading distance
     */
    public Garage(int maxCars, double x, double y, double distance) {
        this.maxCars = maxCars;
        this.x = x;
        this.y = y;
        this.distance = distance;
    }

    /**
     * To access the x-coordinate
     * @return the current value of x
     */
    public double getX() {
        return x;
    }

    /**
     * To access the y-coordinate
     * @return the current value of y
     */
    public double getY() {
        return y;
    }

    /**
     * To access the stored cars
     * @return list of stored cars
     */
    public List<T> getCars() {
        return cars;
    }

    /**
     * Loads a car into the garage
     * @param car car to load
     */
    @Override
    public void load(T car) {
        if (cars.size() < maxCars && inProximity(car) && !cars.contains(car)){
            cars.add(car);
        }
    }

    /**
     * Unloads a car onto the tow truck
     * @param car car to unload
     */
    @Override
    public void unload(T car) {
        if (cars.contains(car)){
            cars.remove(car);
        }
    }


    /**
     * checks if distance to car is in range
     * @param car car to check
     * @return "is car close enough?"
     */
    private boolean inProximity(T car) {
        return getX() - car.getX() <= distance && getX() - car.getX() >= -distance
                && getY() - car.getY() <= distance && getY() - car.getY() >= -distance;
    }
}
