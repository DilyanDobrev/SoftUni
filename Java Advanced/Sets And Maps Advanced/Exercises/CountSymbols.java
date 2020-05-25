package SetsAndMapsAdvancedExercises;

        import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStreamReader;
        import java.util.Map;
        import java.util.TreeMap;

public class CountSymbols {
    public static void main(String[] args) throws IOException {
        BufferedReader reader =
                new BufferedReader(
                        new InputStreamReader(
                                System.in
                        )
                );

        String text = reader.readLine();

        Map<Character, Integer> symbols = new TreeMap<>();

        for (int i = 0; i < text.length(); i++) {
            char symbol = text.charAt(i);
            int value = 1;
            if (!symbols.containsKey(symbol)) {
                symbols.put(symbol, value);
            } else {
                symbols.put(symbol, symbols.get(symbol) + value);
            }
        }
        symbols.forEach((key, value) -> System.out.printf("%c: %d time/s%n", key, value));
    }
}
