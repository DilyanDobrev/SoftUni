package StreamsFilesAndDirectoriesExercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SumBytes {
    public static void main(String[] args) {
        String inputPath =
                "C:\\Users\\didaxcheto\\Desktop\\Java\\Java Advanced 17 Sep 2019\\" +
                        "STREAMS, FILES AND DIRECTORIES\\" +
                        "04. Java-Advanced-Files-and-Streams-Exercises-Resources\\input.txt";

        BigInteger asciiSum = BigInteger.ZERO;
        try {
            BufferedReader reader = Files.newBufferedReader(Paths.get(inputPath));

            String line = reader.readLine();
            while (line != null) {
                for (char symbol : line.toCharArray()) {
                   asciiSum = asciiSum.add(BigInteger.valueOf(symbol));
                }
                line = reader.readLine();
            }
            System.out.println(asciiSum);
        } catch (IOException ex) {
            System.out.println("Really bad!!!");
        }
    }
}
