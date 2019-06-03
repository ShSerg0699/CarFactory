package factory;

public class CarFactory {

    private Storage<Engine> engineStorage = new Storage<>(10);
    private Storage<Carcass> carcassStorage = new Storage<>(10);
    private Storage<Accessory> accessoryStorage = new Storage<>(10);
    private Storage<Car> carStorage = new Storage<>(10);


    private Supplier<Engine> engineSupplier;
    private Supplier<Carcass> carcassSupplier;
    private Supplier<Accessory> accessorySupplier;
    private Worker worker;
    private boolean isRunning = false;

    public void run(){
        if (isRunning) {
            return;
        }
        isRunning = true;
        engineSupplier = new Supplier<>(Engine::new, engineStorage, 5);
        carcassSupplier = new Supplier<>(Carcass::new, carcassStorage, 7);
        accessorySupplier = new Supplier<>(Accessory::new, accessoryStorage, 3);
        worker = new Worker(carStorage, engineStorage, carcassStorage, accessoryStorage);
        engineSupplier.start();
        carcassSupplier.start();
        accessorySupplier.start();
        worker.start();
    }

    public void stop(){
        if(!isRunning){
            return;
        }
        isRunning = false;
        engineSupplier.interrupt();
        carcassSupplier.interrupt();
        accessorySupplier.interrupt();
        worker.interrupt();
    }

    public String getEngineStorageSize(){
        String size = Integer.toString(engineStorage.getSize());
        String maxSize = Integer.toString(engineStorage.getMaxSize());
        return (size + "/" + maxSize);
    }

    public String getCarcassStorageSize(){
        String size = Integer.toString(carcassStorage.getSize());
        String maxSize = Integer.toString(carcassStorage.getMaxSize());
        return (size + "/" + maxSize);
    }

    public String getAccessoryStorageSize(){
        String size = Integer.toString(accessoryStorage.getSize());
        String maxSize = Integer.toString(accessoryStorage.getMaxSize());
        return (size + "/" + maxSize);
    }

    public String getCarStorageSize(){
        String size = Integer.toString(carStorage.getSize());
        String maxSize = Integer.toString(carStorage.getMaxSize());
        return (size + "/" + maxSize);
    }
}
