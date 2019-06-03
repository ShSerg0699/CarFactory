package factory;

@FunctionalInterface
public interface CarPartCreator<T> {
    T createCarPart();
}
