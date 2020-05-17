package StacksAndQueuesExercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class BalancedParentheses {
    public static void main(String[] args) throws IOException {
        BufferedReader reader =
                new BufferedReader(
                        new InputStreamReader(
                                System.in
                        )
                );

        String line = reader.readLine();

        ArrayDeque<Character> stack = new ArrayDeque<>();
        boolean areBalanced = true;
        for (int i = 0; i < line.length(); i++) {
            char symbol = line.charAt(i);
            if (symbol == '{' || symbol == '[' || symbol == '(') {
                stack.push(symbol);
            } else {
                if (stack.isEmpty()) {
                    areBalanced = false;
                    break;
                }
                char last = stack.pop();
                if (last == '{' && symbol != '}') {
                    areBalanced = false;
                    break;
                } else if (last == '[' && symbol != ']') {
                    areBalanced = false;
                    break;
                } else if (last == '(' && symbol != ')') {
                    areBalanced = false;
                    break;
                }
            }
        }
        if (areBalanced) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}
