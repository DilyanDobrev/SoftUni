package FunctionalProgrammingExercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.function.Consumer;

public class ConsumerPrint {
    public static void main(String[] args) {

        BufferedReader reader =
                new BufferedReader(
                        new InputStreamReader(
                                System.in
                        )
                );
        try {
//            String[] input = reader.readLine().split("\\s+");
//            Consumer<String> print = System.out::println;
//            Arrays.stream(input).forEach(print);

            Consumer<String> print = System.out::println;
            Arrays.stream(reader.readLine().split("\\s+")).forEach(print);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
