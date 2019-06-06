package factory;

import java.util.concurrent.atomic.AtomicInteger;

public class Engine {
    private static AtomicInteger forID = new AtomicInteger(10000);
    private final int id;

    public Engine(){
        id = forID.incrementAndGet();
    }

    public int getID() { return id; }
}
