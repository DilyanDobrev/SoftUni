package StacksAndQueuesExercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;

public class BasicQueueOperations {
    public static void main(String[] args) throws IOException {
        BufferedReader reader =
                new BufferedReader(
                        new InputStreamReader(
                                System.in
                        )
                );
        int[] data = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt).toArray();

        ArrayDeque<Integer> queue = new ArrayDeque<>();

        int elemToAdd = data[0];
        int elemToPoll = data[1];
        int present = data[2];

        int[] elements = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt).toArray();

        for (int i = 0; i < elemToAdd; i++) {
            queue.add(elements[i]);
        }
        for (int i = 0; i < elemToPoll; i++) {
            queue.poll();
        }
        if (queue.contains(present)) {
            System.out.println("true");
        } else if (queue.isEmpty()) {
            System.out.println(0);
        } else {
            int min = Integer.MAX_VALUE;
            while (!queue.isEmpty()) {
                int curr = queue.poll();
                if (curr < min) {
                    min = curr;
                }
            }
            System.out.println(min);
        }
    }
}
