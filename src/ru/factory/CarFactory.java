package factory;

import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CarFactory {

    private Storage<Engine> engineStorage = new Storage<>(100);
    private Storage<Carcass> carcassStorage = new Storage<>(100);
    private Storage<Accessory> accessoryStorage = new Storage<>(100);
    private Storage<Car> carStorage = new Storage<>(100);


    private Supplier<Engine> engineSupplier;
    private Supplier<Carcass> carcassSupplier;
    private Supplier<Accessory>[] accessorySuppliers;
    private Worker[] workers;
    private Dealer[] dealers;
    private int workersNum = 10;
    private int dealersNum = 20;
    private int accessoriesSuppliersNum = 5;
    private boolean isRunning = false;

    public void run(int engineSpeed, int carcassSpeed, int accessorySpped, int workerSpeed, int dealerSpeed) {
        if (isRunning) {
            return;
        }
        isRunning = true;
        engineSupplier = new Supplier<>(Engine::new, engineStorage, engineSpeed);
        engineSupplier.start();
        carcassSupplier = new Supplier<>(Carcass::new, carcassStorage, carcassSpeed);
        carcassSupplier.start();
        accessorySuppliers = new Supplier[accessoriesSuppliersNum];
        for (int i = 0; i < accessoriesSuppliersNum; i++) {
            accessorySuppliers[i] = new Supplier<>(Accessory::new, accessoryStorage, accessorySpped);
            accessorySuppliers[i].start();
        }
        workers = new Worker[workersNum];
        for (int i = 0; i < workersNum; i++) {
            workers[i] = new Worker(carStorage, engineStorage, carcassStorage, accessoryStorage, workerSpeed);
            workers[i].start();
        }
        dealers = new Dealer[dealersNum];
        for (int i = 0; i < dealersNum; i++) {
            dealers[i] = new Dealer(carStorage, dealerSpeed);
            dealers[i].start();
        }
    }

    public void stop() {
        if (!isRunning) {
            return;
        }
        isRunning = false;
        engineSupplier.interrupt();
        carcassSupplier.interrupt();
        for (int i = 0; i < accessoriesSuppliersNum; i++) {
            accessorySuppliers[i].interrupt();
        }
        for (int i = 0; i < workersNum; i++) {
            workers[i].interrupt();
        }
        for (int i = 0; i < dealersNum; i++) {
            dealers[i].interrupt();
        }
    }

    public String getEngineStorageSize() {
        String size = Integer.toString(engineStorage.getSize());
        String maxSize = Integer.toString(engineStorage.getMaxSize());
        return (size + "/" + maxSize);
    }

    public String getCarcassStorageSize() {
        String size = Integer.toString(carcassStorage.getSize());
        String maxSize = Integer.toString(carcassStorage.getMaxSize());
        return (size + "/" + maxSize);
    }

    public String getAccessoryStorageSize() {
        String size = Integer.toString(accessoryStorage.getSize());
        String maxSize = Integer.toString(accessoryStorage.getMaxSize());
        return (size + "/" + maxSize);
    }

    public String getCarStorageSize() {
        String size = Integer.toString(carStorage.getSize());
        String maxSize = Integer.toString(carStorage.getMaxSize());
        return (size + "/" + maxSize);
    }


    /*public class CarStorageController implements Observer {
        private ExecutorService workerPool;
        private int workerSpeed;

        private final Runnable workerTask = () -> {
            try {
                TimeUnit.MILLISECONDS.sleep(workerSpeed * 1000);
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

        };

        CarStorageController(int workerSpeed) {
            carStorage.addObserver(this);
            workerPool = Executors.newFixedThreadPool(workersNum);
            this.workerSpeed = workerSpeed;
        }

        @Override
        public void update(Observable storage, Object arg) {
            Storage<Car> carStorage = (Storage<Car>) storage;

        }
    }*/

}
