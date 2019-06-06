package factory;

import java.util.concurrent.TimeUnit;

public class Worker extends Thread{
    private Storage<Car> carStorage;
    private Storage<Engine> engineStorage;
    private Storage<Carcass> carcassStorage;
    private Storage<Accessory> accessoryStorage;
    private int speed;

    public Worker(Storage<Car> carStorage,
                  Storage<Engine> engineStorage,
                  Storage<Carcass> carcassStorage,
                  Storage<Accessory> accessoryStorage,
                  int speed) {
        this.carStorage = carStorage;
        this.engineStorage = engineStorage;
        this.carcassStorage = carcassStorage;
        this.accessoryStorage = accessoryStorage;
        this.speed = speed;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                TimeUnit.MILLISECONDS.sleep(speed * 1000);
                Engine engine = engineStorage.get();
                Carcass carcass = carcassStorage.get();
                Accessory accessory = accessoryStorage.get();
                Car car = new Car(engine, carcass, accessory);
                carStorage.add(car);
                System.out.println("WORKER: car add");
            } catch (InterruptedException exc) {
                System.out.println("interrupted");
                return;
            }
        }

    }
}
