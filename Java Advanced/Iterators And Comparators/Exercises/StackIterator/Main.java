package IteratorsAndComparatorsExercises.StackIterator;

import javax.naming.OperationNotSupportedException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Stack stack = new Stack();

        String input;
        while (!"END".equals(input = scanner.nextLine())) {
            if (input.contains("Push")) {
                Arrays.stream(input.split("([, ]+)"))
                        .skip(1)
                        .mapToInt(Integer::parseInt)
                        .forEach(stack::push);
            } else {
                try {
                    stack.pop();
                } catch (OperationNotSupportedException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        for (int i = 0; i < 2; i++) {
            for (Integer e : stack) {
                System.out.println(e);
            }
        }
    }
}
