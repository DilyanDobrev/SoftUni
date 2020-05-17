package StacksAndQueuesExercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class SimpleTextEditor {
    public static void main(String[] args) throws IOException {
        BufferedReader reader =
                new BufferedReader(
                        new InputStreamReader(
                                System.in
                        )
                );

        int n = Integer.parseInt(reader.readLine());

        StringBuilder sb = new StringBuilder();

        ArrayDeque<StringBuilder> stack = new ArrayDeque<>();

        while (n-- > 0) {
            String[] data = reader.readLine().split("\\s+");

            String command = data[0];
            //stack.push(sb);
            switch (command) {
                case "1":
                    sb.append(data[1]);
                    stack.push(new StringBuilder(sb));
                    break;
                case "2":
                    int count = Integer.parseInt(data[1]);
                    int start = sb.length() - count;
                    sb.delete(start, start + count);
                    stack.push(new StringBuilder(sb));
                    break;
                case "3":
                    System.out.println(sb.charAt(Integer.parseInt(data[1]) - 1));
                    break;
                case "4":
                    if (stack.size() > 1) {
                        stack.pop();
                        sb = stack.peek();
                    } else if (stack.size() == 1){
                        sb = new StringBuilder();
                }
                    break;
            }

        }
    }
}
