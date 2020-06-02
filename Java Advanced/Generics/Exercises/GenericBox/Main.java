package GenericsExercises.GenericBox;

import java.util.Comparator;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        scanner.nextLine();

        while (n-- > 0) {
            String input = scanner.nextLine();

            Box<String> data = new Box<>(input);

            System.out.println(data);
        }
    }
}
