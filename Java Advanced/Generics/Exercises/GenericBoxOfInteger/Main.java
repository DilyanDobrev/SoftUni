package GenericsExercises.GenericBoxOfInteger;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        scanner.nextLine();

        while (n-- > 0) {
            int input = Integer.parseInt(scanner.nextLine());

            Box<Integer> data = new Box<>(input);

            System.out.println(data);
        }
    }
}
