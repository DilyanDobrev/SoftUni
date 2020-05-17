package StacksAndQueuesExercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RecursiveFibonacci {

    private static long[] memory;

    public static void main(String[] args) throws IOException {
        BufferedReader reader =
                new BufferedReader(
                        new InputStreamReader(
                                System.in
                        )
                );

        int n = Integer.parseInt(reader.readLine());

        memory = new long[n + 1];

        long result = fibonacci(n);
        System.out.println(result);
    }

    private static long fibonacci(int n) {
        if (n < 2){
            return 1;
        }
        if (memory[n] != 0){
            return memory[n];
        }
        return memory[n] = fibonacci(n - 1) + fibonacci(n - 2);
    }
}
