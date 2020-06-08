package Exam26October2019.rabbits;

import java.util.ArrayList;
import java.util.List;

public class Cage {
    private String name;
    private int capacity;
    private List<Rabbit> data;

    public Cage(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.data = new ArrayList<>();
    }

    public String getName() {
        return this.name;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public int getSize() {
        return this.data.size();
    }

    public void add(Rabbit rabbit) {
        if (this.getSize() < this.getCapacity()) {
            this.data.add(rabbit);
        }
    }

    public boolean removeRabbit(String name) {
        return this.data.removeIf(r -> r.getName().equals(name));
    }

    public void removeSpecies(String species) {
        this.data.removeIf(r -> r.getSpecies().equals(species));
    }

    public Rabbit sellRabbit(String name) {
        Rabbit rabbit = null;
        for (Rabbit datum : this.data) {
            if (datum.getName().equals(name)) {
                datum.setAvailable(false);
                rabbit = datum;
                break;
            }
        }
        return rabbit;
    }

    public List<Rabbit> sellRabbitBySpecies(String species) {
        List<Rabbit> list = new ArrayList<>();

        for (Rabbit datum : this.data) {
            if (datum.getSpecies().equals(species)) {
                list.add(datum);
                datum.setAvailable(false);
            }
        }

        return list;
    }

    public int count() {
        return this.data.size();
    }

    public String report() {
        StringBuilder builder = new StringBuilder();

        builder.append(String.format("Rabbits available at %s:", this.name));
        builder.append(System.lineSeparator());
        for (Rabbit rabbit : this.data) {
            if (rabbit.isAvailable()) {
                builder.append(rabbit).append(System.lineSeparator());
            }
        }
        return builder.toString();
    }
}
