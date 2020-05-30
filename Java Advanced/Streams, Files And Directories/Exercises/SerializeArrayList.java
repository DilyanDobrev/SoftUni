package StreamsFilesAndDirectoriesExercises;

import java.io.*;
import java.util.ArrayList;

public class SerializeArrayList {
    public static void main(String[] args) {

        String inputPath =
                "C:\\Users\\didaxcheto\\Desktop\\Java\\Java Advanced 17 Sep 2019\\" +
                        "STREAMS, FILES AND DIRECTORIES\\" +
                        "04. Java-Advanced-Files-and-Streams-Exercises-Resources\\";

        ArrayList<Double> doubles = new ArrayList<>();
        doubles.add(3.14);
        doubles.add(1.65);
        doubles.add(13.27);
        doubles.add(42.69);

        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(inputPath + "doubles.ser"));
            oos.writeObject(doubles);
            oos.close();

            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(inputPath + "doubles.ser"));
            Object o = ois.readObject();
            if (o instanceof ArrayList) {
               var list = (ArrayList<Double>) o;
                System.out.println();
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
