package StacksAndQueuesExercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;

public class BasicStackOperations {

    public static void main(String[] args) throws IOException {
        BufferedReader reader =
                new BufferedReader(
                        new InputStreamReader(
                                System.in
                        )
                );

        int[] data = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt).toArray();

        ArrayDeque<Integer> stack = new ArrayDeque<>();

        int elemToPush = data[0];
        int elemToPop = data[1];
        int present = data[2];

        Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .limit(elemToPush)
                .forEach(stack::push);

        for (int i = 0; i < elemToPop; i++) {
            stack.pop();
        }
        if (stack.contains(present)) {
            System.out.println("true");
        } else if (stack.isEmpty()) {
            System.out.println(0);
        } else {
            int min = Integer.MAX_VALUE;
            while (!stack.isEmpty()) {
                int curr = stack.pop();
                if (curr < min) {
                    min = curr;
                }
            }
            System.out.println(min);
        }
    }
}
