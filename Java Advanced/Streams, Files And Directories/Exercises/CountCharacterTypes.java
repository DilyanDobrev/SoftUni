package StreamsFilesAndDirectoriesExercises;

import java.io.*;

public class CountCharacterTypes {
    public static void main(String[] args) {

        String inputPath =
                "C:\\Users\\didaxcheto\\Desktop\\Java\\Java Advanced 17 Sep 2019\\" +
                        "STREAMS, FILES AND DIRECTORIES\\" +
                        "04. Java-Advanced-Files-and-Streams-Exercises-Resources\\input.txt";

        String outputPath =
                "C:\\Users\\didaxcheto\\Desktop\\Java\\Java Advanced 17 Sep 2019\\" +
                        "STREAMS, FILES AND DIRECTORIES\\" +
                        "04. Java-Advanced-Files-and-Streams-Exercises-Resources\\outputCharacters.txt";

        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputPath));
            PrintWriter writer = new PrintWriter(new FileWriter(outputPath));

            String vowels = "aeiou";
            String punctuation = "!,.?";

            int[] symbols = new int[3];

            String line = reader.readLine();
            while (line != null) {
                for (char c : line.toCharArray()) {
                    if (c != ' ') {
                        if (vowels.contains(String.valueOf(c))) {
                            symbols[0]++;
                        } else if (punctuation.contains(String.valueOf(c))) {
                            symbols[2]++;
                        } else {
                            symbols[1]++;
                        }
                    }
                }
                line = reader.readLine();
            }
            writer.println("Vowels: " + symbols[0] + "");
            writer.println("Consonants: " + symbols[1] + "");
            writer.println("Punctuation: " + symbols[2] + "");
            writer.close();
        } catch (IOException ex) {
            System.out.println("Really bad!!!");
        }
    }

}
