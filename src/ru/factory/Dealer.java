package factory;

import java.util.concurrent.TimeUnit;

public class Dealer extends Thread{
    private Storage<Car> carStorage;
    private int speed;

    public Dealer(Storage<Car> carStorage, int speed) {
        this.carStorage = carStorage;
        this.speed = speed;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                TimeUnit.MILLISECONDS.sleep(speed * 1000);
                Car car = carStorage.get();
                System.out.println("DEALER: Car: " + car.getID() +
                                    " Engine: " + car.getEngineID() +
                                    " Carcass: " + car.getCarcassID() +
                                    " Accessory: " + car.getAccessoryID());
            } catch (InterruptedException exc) {
                System.out.println("interrupted");
                return;
            }
        }

    }
}
