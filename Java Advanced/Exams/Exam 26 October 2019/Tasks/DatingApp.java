package Exam26October2019;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;

public class DatingApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayDeque<Integer> males = new ArrayDeque<>();

        Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .forEach(males::push);

        ArrayDeque<Integer> females = new ArrayDeque<>();

        Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .forEach(females::offer);
        int matches = 0;
        while (!females.isEmpty() && !males.isEmpty()) {
            int female = females.peek();
            int male = males.peek();

            if (female <= 0) {
                females.poll();
                continue;
            }
            if (male <= 0) {
                males.pop();
                continue;
            }
            if (female % 25 == 0 && female > 0) {
                females.poll();
                females.poll();
                continue;
            }
            if (male % 25 == 0 && male > 0) {
                males.pop();
                males.pop();
                continue;
            }

            if (female == male) {
                females.poll();
                males.pop();
                matches++;
            } else {
                females.poll();
                males.pop();
                males.push(male - 2);
            }
        }
        System.out.println("Matches: " + matches);

        String outputM = "Males left: ";
        if (males.isEmpty()) {
            outputM += "none";
        } else {
            outputM += males.toString().replaceAll("[\\[\\]]", "");
        }
        System.out.println(outputM);

        String outputF = "Females left: ";
        if (females.isEmpty()) {
            outputF += "none";
        } else {
            outputF += females.toString().replaceAll("[\\[\\]]", "");
        }
        System.out.println(outputF);
    }
}
