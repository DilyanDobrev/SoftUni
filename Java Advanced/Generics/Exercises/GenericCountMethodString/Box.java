package GenericsExercises.GenericCountMethodString;

public class Box<T extends Comparable<T>> implements Comparable<Box<T>> {
    private T element;

    public Box(T element) {
        this.element = element;
    }

    @Override
    public int compareTo(Box<T> other) {
        return this.element.compareTo(other.element);
    }
}
