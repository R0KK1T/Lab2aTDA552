import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class CarTest {
    @Test
    public void TestTurbo() {
        Saab95 saab = new Saab95(Color.black);
        saab.startEngine();
        saab.setTurboOn();
        saab.gas(0.5);
        assertTrue(saab.getCurrentSpeed() == 0.9125);
    }

    @Test
    public void StartAndGasTest() {
        Saab95 saab = new Saab95(Color.black);
        Volvo240 volvo = new Volvo240(Color.red);
        saab.startEngine();
        volvo.startEngine();
        saab.gas(0.5);
        volvo.gas(0.5);
        assertTrue(saab.getCurrentSpeed() == 0.725);
        assertTrue(volvo.getCurrentSpeed() == 0.725);
        saab.gas(1);
        volvo.gas(1);
        assertTrue(saab.getCurrentSpeed() == 1.975);
        assertTrue(volvo.getCurrentSpeed() == 1.975);

    }

    @Test
    public void TurnAndMoveTest() {
        Saab95 saab = new Saab95(Color.black);
        Volvo240 volvo = new Volvo240(Color.red);
        saab.startEngine();
        volvo.startEngine();
        saab.turnLeft(Math.PI / 2);
        volvo.turnRight(Math.PI);
        saab.move();
        volvo.move();
        assertTrue(saab.getY() == -0.1);
        assertTrue(volvo.getX() == -0.1);
    }

    @Test
    public void BreakTest(){
        Saab95 saab = new Saab95(Color.black);
        Volvo240 volvo = new Volvo240(Color.red);
        saab.gas(1);
        volvo.gas(0.25);
        saab.brake(1);
        volvo.brake(0.25);
        assertTrue(saab.getCurrentSpeed() == 0);
        assertTrue(volvo.getCurrentSpeed() == 0);
    }

    @Test
    public void FlakTest(){
        Scania car = new Scania(Color.BLUE);
        car.increaseAngle(49);
        car.decreaseAngle(13);
        assertTrue(car.getTippingAngle() == 36);

        car.increaseAngle(49);
        assertTrue(car.getTippingAngle() == 70);

        car.decreaseAngle(100);
        assertTrue(car.getTippingAngle() == 0);
    }

    @Test
    public void AngledGasTest() {
        Scania car = new Scania(Color.BLUE);
        car.startEngine();
        car.increaseAngle(45);
        assertTrue(car.getTippingAngle() == 0);
        car.stopEngine();
        car.increaseAngle(45);
        car.startEngine();
        assertTrue(car.getCurrentSpeed() == 0);
        //SpeedFactor is never used
    }

    @Test
    public void LoadAndUnloadTest(){
        Volvo240 volvo = new Volvo240(Color.RED);
        Saab95 saab = new Saab95(Color.black);
        TowTruck truck = new TowTruck(Color.black, 3);
        truck.openRamp();
        truck.load(volvo);
        truck.load(saab);
        truck.closeRamp();
        truck.startEngine();
        truck.move();
        assertTrue(truck.getStoredCars().contains(volvo));
        assertTrue(truck.getX() == 0.1);
        assertTrue(volvo.getX() == 0.1);
        assertTrue(saab.getX() == 0.1);
        truck.stopEngine();
        truck.openRamp();
        truck.unload(volvo);
        truck.unload(saab);
        assertTrue(truck.getStoredCars().size() == 0);
    }

    @Test
    public void garageTest(){
        Garage<Volvo240> volvo240Garage = new Garage<>(10, 3,3,20);
        Volvo240 volvo1 = new Volvo240(Color.black);
        Volvo240 volvo2 = new Volvo240(Color.black);
        Volvo240 volvo3 = new Volvo240(Color.black);
        Volvo240 volvo4 = new Volvo240(Color.black);
        Saab95 saab95 = new Saab95(Color.black);
        volvo240Garage.load(volvo1);
        volvo240Garage.load(volvo2);
        volvo240Garage.load(volvo3);
        volvo240Garage.load(volvo4);
        //volvo240Garage.load(saab95);
        volvo240Garage.unload(volvo2);
        assertTrue(volvo240Garage.getCars().size() == 3);
        assertTrue(!volvo240Garage.getCars().contains(volvo2));


    }


}