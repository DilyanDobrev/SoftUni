package IteratorsAndComparatorsExercises.ListyIterator;

import java.util.Iterator;
import java.util.List;

public class ListyIterator implements Iterable<String> {
    private List<String> data;
    private int index;

    public ListyIterator(List<String> data) {
        this.data = data;
        this.index = 0;
    }

    public boolean Move() {
        if (HasNext()) {
            this.index++;
            return true;
        }
        return false;
    }

    public boolean HasNext() {
        if (this.index < this.data.size() - 1) {
            return true;
        }
        return false;
    }

    public String print() throws IllegalAccessException {
        if (this.data.size() == 0) {
            throw new IllegalAccessException("Invalid Operation!");
        }
        return this.data.get(this.index);
    }

    public String PrintAll() {
        String output = "";
        for (String s : this) {
            output += s + " ";
        }
        return output;
    }

    @Override
    public Iterator<String> iterator() {
        return new Iterator<>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return this.index < data.size();
            }

            @Override
            public String next() {
                return data.get(this.index++);
            }
        };
    }

}
