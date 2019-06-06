package factory;

import java.util.concurrent.atomic.AtomicInteger;

public class Car {
    private static AtomicInteger forID = new AtomicInteger(40000);
    private final Engine engine;
    private final Carcass carcass;
    private final Accessory accessory;
    private final int id;

    public Car(Engine engine, Carcass carcass, Accessory accessory) {
        this.engine = engine;
        this.carcass = carcass;
        this.accessory = accessory;
        id = forID.incrementAndGet();
    }

    public int getID() { return id; }

    public int getCarcassID(){
        return carcass.getID();
    }

    public int getEngineID(){
        return engine.getID();
    }

    public int getAccessoryID(){
        return accessory.getID();
    }
}

