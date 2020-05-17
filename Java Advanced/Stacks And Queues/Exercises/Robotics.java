package StacksAndQueuesExercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class Robotics {
    public static void main(String[] args) throws IOException {
        BufferedReader reader =
                new BufferedReader(
                        new InputStreamReader(
                                System.in
                        )
                );

        String[] input = reader.readLine().split(";");

        String[] robots = new String[input.length];
        int[] processTime = new int[input.length];
        int[] workTime = new int[input.length];

        for (int i = 0; i < input.length; i++) {
            String[] data = input[i].split("-");
            String name = data[0];
            int time = Integer.parseInt(data[1]);
            robots[i] = name;
            processTime[i] = time;
        }

        String startTime = reader.readLine();

        ArrayDeque<String> products = new ArrayDeque<>();

        String inputProduct;

        while (!"End".equals(inputProduct = reader.readLine())) {
            products.offer(inputProduct);
        }

        String[] timeData = startTime.split(":");
        int h = Integer.parseInt(timeData[0]);
        int m = Integer.parseInt(timeData[1]);
        int s = Integer.parseInt(timeData[2]);

        int beginSeconds = h * 3600 + m * 60 + s;

        while (!products.isEmpty()) {
            beginSeconds++;
            String product = products.poll();
            boolean isAssigned = false;
            for (int i = 0; i < robots.length; i++) {
                if (workTime[i] == 0 && !isAssigned) {
                    workTime[i] = processTime[i];
                    isAssigned = true;
                    print(robots[i], product, beginSeconds);
                }
                if (workTime[i] > 0) {
                    workTime[i]--;
                }
            }
            if (!isAssigned) {
                products.offer(product);
            }
        }
    }

    private static void print(String robot, String product, int beginSeconds) {
        long s = beginSeconds % 60;
        long m = (beginSeconds / 60) % 60;
        long h = (beginSeconds / 3600) % 24;

        System.out.println(String.format("%s - %s [%02d:%02d:%02d]", robot, product, h, m, s));
    }
}
