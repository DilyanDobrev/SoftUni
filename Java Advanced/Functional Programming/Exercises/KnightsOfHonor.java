package FunctionalProgrammingExercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class KnightsOfHonor {
    public static void main(String[] args) {
        BufferedReader reader =
                new BufferedReader(
                        new InputStreamReader(
                                System.in
                        )
                );
        try {
            ArrayList<String> names = Arrays.stream(reader.readLine().split("\\s+"))
                    .collect(Collectors.toCollection(ArrayList::new));
            Consumer<String> appendAndPrint = name -> System.out.println("Sir " + name);
            names.forEach(appendAndPrint);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
