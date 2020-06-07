package IteratorsAndComparatorsExercises.StrategyPattern;

import java.util.Comparator;

public class PersonCompareByAge implements Comparator<Person> {
    @Override
    public int compare(Person f, Person s) {
        return f.getAge() - s.getAge();
    }
}
