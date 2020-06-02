package GenericsExercises.GenericCountMethodString;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        List<Box<Double>> boxes = new ArrayList<>();

        while (n-- > 0) {
            String input = scanner.nextLine();

            Box<Double> box = new Box<>(Double.parseDouble(input));
            boxes.add(box);
        }
        Box<Double> compare = new Box<>(Double.parseDouble(scanner.nextLine()));
        int count = countElements(boxes, compare);

        System.out.println(count);
    }

    private static <T extends Comparable<T>> int countElements(List<Box<T>> boxes, Box<T> compare) {
        int count = 0;
        for (Box<T> box : boxes) {
            if (box.compareTo(compare) > 0) {
                count++;
            }
        }
        return count;
    }
}
