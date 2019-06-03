package factory;

public class Supplier<T> extends Thread{
    private Storage<T> storage;
    private CarPartCreator<T> carPartCreator;
    private int speed;

    public Supplier(CarPartCreator<T> carPartCreator, Storage<T> storage, int speed){
            this.storage = storage;
            this.carPartCreator = carPartCreator;
            this.speed = speed;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                storage.add(carPartCreator.createCarPart());
                Thread.sleep(speed * 1000);
            } catch (InterruptedException e) {
                System.out.println("interrupted");
                return;
            }
        }
    }

}
