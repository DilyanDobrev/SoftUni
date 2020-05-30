package StreamsFilesAndDirectoriesExercises;

import java.io.*;

public class AllCapitals {
    public static void main(String[] args) {

        String inputPath =
                "C:\\Users\\didaxcheto\\Desktop\\Java\\Java Advanced 17 Sep 2019\\" +
                        "STREAMS, FILES AND DIRECTORIES\\" +
                        "04. Java-Advanced-Files-and-Streams-Exercises-Resources\\input.txt";

        String outputPath =
                "C:\\Users\\didaxcheto\\Desktop\\Java\\Java Advanced 17 Sep 2019\\" +
                        "STREAMS, FILES AND DIRECTORIES\\" +
                        "04. Java-Advanced-Files-and-Streams-Exercises-Resources\\output.txt";

        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputPath));
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath));
            String line = reader.readLine();
            while (line != null) {
                line = line.toUpperCase();
                writer.write(line);
                writer.newLine();
                writer.flush();
                line = reader.readLine();
            }
        } catch (IOException ex) {
            System.out.println("Really bad!!!");
        }
    }
}
