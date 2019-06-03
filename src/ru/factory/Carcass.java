package factory;

import java.util.concurrent.atomic.AtomicInteger;

public class Carcass {
    private static AtomicInteger forID = new AtomicInteger(20000);
    private int id;

    public Carcass(){
        id = forID.incrementAndGet();
    }
}
