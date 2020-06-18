package interfacesandabstraction.telephony;

import java.util.List;

public class Smartphone implements Callable, Browsable {
    private List<String> numbers;
    private List<String> urls;

    public Smartphone(List<String> numbers, List<String> urls) {
        this.numbers = numbers;
        this.urls = urls;
    }

    @Override
    public String browse() {
        StringBuilder builder = new StringBuilder();

        for (String url : this.urls) {
            boolean isValid = validateURLs(url);
            if (isValid) {
                builder.append("Browsing: ").append(url).append("!");
            } else {
                builder.append("Invalid URL!");
            }
            builder.append(System.lineSeparator());
        }
        return builder.toString().trim();
    }

    @Override
    public String call() {
       StringBuilder builder = new StringBuilder();

        for (String number : this.numbers) {
            boolean isValid = validateNumber(number);
            if (isValid) {
                builder.append("Calling... ").append(number);
            } else {
                builder.append("Invalid number!");
            }
            builder.append(System.lineSeparator());
        }
        return builder.toString().trim();
    }
    
    private boolean validateNumber(String number) {
        for (char symbol : number.toCharArray()) {
            if (!Character.isDigit(symbol)) {
                return false;
            }
        }
        return true;
    }

    private boolean validateURLs(String url) {
        for (char symbol : url.toCharArray()) {
            if (Character.isDigit(symbol)) {
                return false;
            }
        }
        return true;
    }
}
