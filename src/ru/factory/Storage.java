package factory;

import java.util.LinkedList;


public class Storage<T> {
    private int maxSize;
    private LinkedList<T> storage = new LinkedList<>();

    public Storage(int maxSize) {
        this.maxSize = maxSize;
    }

    public synchronized void add(T element) throws InterruptedException {
        while (true) {
            if (storage.size() < maxSize) {
                storage.add(element);
                this.notify();
                return;
            } else {
                this.wait();
            }
        }
    }

    public synchronized T get() throws InterruptedException {
        while (true) {
            if (storage.size() > 0) {
                this.notify();
                return storage.poll();
            } else {

                this.wait();
            }
        }
    }

    public int getSize(){
        return storage.size();
    }

    public int getMaxSize(){
        return maxSize;
    }
}