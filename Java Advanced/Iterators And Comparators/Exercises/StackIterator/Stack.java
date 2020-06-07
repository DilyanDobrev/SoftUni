package IteratorsAndComparatorsExercises.StackIterator;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Stack implements Iterable<Integer> {
    private List<Integer> data;
    private int index;

    public Stack() {
        this.data = new ArrayList<>();
        this.index = -1;
    }

    public void push(int element) {
        this.data.add(element);
        this.index++;
    }

    public void pop() throws OperationNotSupportedException {
        if (this.index < 0) {
            throw new OperationNotSupportedException("No elements");
        }
        this.data.remove(this.index--);
    }



    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            private int position = data.size() - 1;
            @Override
            public boolean hasNext() {
                return position >= 0;
            }

            @Override
            public Integer next() {
                return data.get(position--);
            }
        };
    }
}
