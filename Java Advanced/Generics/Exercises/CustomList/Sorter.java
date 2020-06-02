package GenericsExercises.CustomList;

import java.util.Arrays;
import java.util.List;

public class Sorter {
    public static <T extends Comparable> void sort(SmartList<String> smartList) {
        String[] sortedCollection = smartList.getCollection();
        Arrays.sort(sortedCollection);
        smartList.setCollection(sortedCollection);
    }
}
