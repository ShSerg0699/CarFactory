package factory;

public class Engine {
    private static int forID = 10000;
    private int id;

    public Engine(){
        forID++;
        id = forID;
    }
}
