package GenericsExercises.Tuple;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        String firstName = input.substring(input.charAt(0), input.lastIndexOf(" "));
        String lastName = input.substring(input.lastIndexOf(" ") + 1);

        System.out.println(new Tuple<String,String>(firstName,lastName));
    }
}
