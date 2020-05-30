package StreamsFilesAndDirectoriesExercises;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class MergeTwoFiles {
    public static void main(String[] args) {

        String dir = "C:\\Users\\didaxcheto\\Desktop\\Java\\Java Advanced 17 Sep 2019\\" +
                "STREAMS, FILES AND DIRECTORIES\\" +
                "04. Java-Advanced-Files-and-Streams-Exercises-Resources\\";

        String firstPath = dir + "inputOne.txt";
        String secondPath = dir + "inputTwo.txt";

        try {
            List<String> lines = Files.lines(Paths.get(firstPath)).collect(Collectors.toList());
            lines.addAll(Files.lines(Paths.get(secondPath)).collect(Collectors.toList()));

            Files.write(Paths.get(dir + "combined.txt"), lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
