package factory;

import java.util.concurrent.atomic.AtomicInteger;

public class Carcass {
    private static AtomicInteger forID = new AtomicInteger(20000);
    private final int id;

    public Carcass(){
        id = forID.incrementAndGet();
    }

    public int getID() { return id; }
}
