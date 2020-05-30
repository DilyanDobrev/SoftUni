package StreamsFilesAndDirectoriesExercises;

import java.awt.print.Printable;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class GetFolderSize {
    public static void main(String[] args) {

        String dir = "C:\\Users\\didaxcheto\\Desktop\\Java\\Java Advanced 17 Sep 2019\\" +
                "STREAMS, FILES AND DIRECTORIES\\" +
                "04. Java-Advanced-Files-and-Streams-Exercises-Resources\\";

        String inputPath = dir + "Exercises Resources";
        String outputPath = dir + "outputSize.txt";


        try {
            File file = new File(inputPath);
            File[] files = file.listFiles();
            int size = 0;
            for (File f : files) {
                size += f.length();
            }
            PrintWriter writer = new PrintWriter(outputPath);
            writer.println("Folder size: " + size);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
