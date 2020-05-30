package StreamsFilesAndDirectoriesExercises;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class WordCount {
    public static void main(String[] args) {

        String dir = "C:\\Users\\didaxcheto\\Desktop\\Java\\Java Advanced 17 Sep 2019\\" +
                "STREAMS, FILES AND DIRECTORIES\\" +
                "04. Java-Advanced-Files-and-Streams-Exercises-Resources\\";

        String firstPath = dir + "words.txt";
        String secondPath = dir + "text.txt";

        String outputPath = dir + "results.txt";


        try  {
            String[] keys = new BufferedReader(new FileReader(firstPath)).readLine().split("\\s+");

            Map<String, Integer> words = new HashMap<>();

            for (String key : keys) {
               words.putIfAbsent(key, 0);
            }

            Files.lines(Paths.get(secondPath)).forEach(line -> {
                Arrays.stream(line.split("[., \\-]+")).forEach(str -> {
                    if (words.containsKey(str)) {
                        words.put(str, words.get(str) + 1);
                    }
                });
            });
            PrintWriter writer = new PrintWriter(outputPath);
            words.entrySet().stream().sorted((f,s) -> s.getValue().compareTo(f.getValue())).forEach(entry -> {
                writer.printf("%s - %d%n", entry.getKey(), entry.getValue());
            });
            writer.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
