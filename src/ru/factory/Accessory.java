package factory;

public class Accessory {
    private static int forID = 30000;
    private int id;

    public Accessory(){
        forID++;
        id = forID;
    }
}
