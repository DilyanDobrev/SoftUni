package IteratorsAndComparatorsExercises.StrategyPattern;

import java.util.Comparator;

public class PersonCompareByName implements Comparator<Person> {
    @Override
    public int compare(Person f, Person s) {
        int result =  f.getName().length() - s.getName().length();
        if (result == 0) {
            char first = Character.toLowerCase(f.getName().charAt(0));
            char second = Character.toLowerCase(s.getName().charAt(0));
            result = first - second;
        }
        return result;
    }
}
