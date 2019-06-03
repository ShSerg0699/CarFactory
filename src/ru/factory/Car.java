package factory;

public class Car {
    private static int forID = 40000;
    private Engine engineID;
    private Carcass carcassID;
    private Accessory accessoryID;
    private int id;

    public Car(Engine engine, Carcass carcass, Accessory accessory) {
        engineID = engine;
        carcassID = carcass;
        accessoryID = accessory;
        forID++;
        id = forID;
    }
}

