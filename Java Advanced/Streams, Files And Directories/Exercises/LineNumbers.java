package StreamsFilesAndDirectoriesExercises;

import java.io.*;

public class LineNumbers {
    public static void main(String[] args) {

//        String dir = System.getProperty("user.dir");

        String dir = "C:\\Users\\didaxcheto\\Desktop\\Java\\Java Advanced 17 Sep 2019\\" +
                "STREAMS, FILES AND DIRECTORIES\\" +
                "04. Java-Advanced-Files-and-Streams-Exercises-Resources\\";

        String inputPath = dir + "inputLineNumbers.txt";

        String outputPath = dir + "outputLineNumbers.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(inputPath));
             PrintWriter writer = new PrintWriter(outputPath)) {

            String line = reader.readLine();
            for (int i = 1; line != null; i++) {
                writer.printf("%s. %s%n", i, line);
                line = reader.readLine();
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
