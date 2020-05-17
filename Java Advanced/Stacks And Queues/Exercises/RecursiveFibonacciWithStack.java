package StacksAndQueuesExercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class RecursiveFibonacciWithStack {
    public static void main(String[] args) throws IOException {
        BufferedReader reader =
                new BufferedReader(
                        new InputStreamReader(
                                System.in
                        )
                );

        int n = Integer.parseInt(reader.readLine());

        ArrayDeque<Long> stack = new ArrayDeque<>();

        stack.push(1L);
        stack.push(1L);

        while (n-- > 1) {
            long a = stack.pop();
            long b = stack.pop();
            long c = a + b;

            stack.push(b);
            stack.push(a);
            stack.push(c);
        }
        System.out.println(stack.pop());
    }
}
