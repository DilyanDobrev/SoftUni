package StreamsFilesAndDirectoriesExercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SumLines {
    public static void main(String[] args) {

        String inputPath =
                "C:\\Users\\didaxcheto\\Desktop\\Java\\Java Advanced 17 Sep 2019\\" +
                        "STREAMS, FILES AND DIRECTORIES\\" +
                        "04. Java-Advanced-Files-and-Streams-Exercises-Resources\\input.txt";


        try {
            BufferedReader reader = Files.newBufferedReader(Paths.get(inputPath));
            String line = reader.readLine();
            while (line != null) {
                long asciiSum = 0;
                for (char symbol : line.toCharArray()) {
                    asciiSum += symbol;
                }
                System.out.println(asciiSum);
                line = reader.readLine();
            }
        } catch (IOException ex) {
            System.out.println("Really bad!!!");
        }
    }
}
