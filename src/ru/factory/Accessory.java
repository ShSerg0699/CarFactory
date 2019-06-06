package factory;

import java.util.concurrent.atomic.AtomicInteger;

public class Accessory {
    private static AtomicInteger forID = new AtomicInteger(30000);
    private final int id;

    public Accessory(){
        id = forID.incrementAndGet();
    }

    public int getID() { return id; }

}
