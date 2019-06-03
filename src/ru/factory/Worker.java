package factory;

import java.util.concurrent.TimeUnit;

public class Worker extends Thread{
    private Storage<Car> carStorage;
    private Storage<Engine> engineStorage;
    private Storage<Carcass> carcassStorage;
    private Storage<Accessory> accessoryStorage;

    public Worker(Storage<Car> carStorage,
                  Storage<Engine> engineStorage,
                  Storage<Carcass> carcassStorage,
                  Storage<Accessory> accessoryStorage) {
        this.carStorage = carStorage;
        this.engineStorage = engineStorage;
        this.carcassStorage = carcassStorage;
        this.accessoryStorage =accessoryStorage;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Engine engine = engineStorage.get();
                Carcass carcass = carcassStorage.get();
                Accessory accessory = accessoryStorage.get();
                Car car = new Car(engine, carcass, accessory);
                carStorage.add(car);
                System.out.println("Car: " + carStorage.getSize());
                TimeUnit.MILLISECONDS.sleep(10000);
            } catch (InterruptedException exc) {
                System.out.println("interrupted");
                return;
            }
        }

    }
}
