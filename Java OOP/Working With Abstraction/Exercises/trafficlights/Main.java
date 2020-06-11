package workingwithabstraction.trafficlights;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] input = scanner.nextLine().split("\\s+");
        int n = Integer.parseInt(scanner.nextLine());

        TrafficLights[] lights = new TrafficLights[input.length];

        for (int i = 0; i < lights.length; i++) {
            lights[i] = TrafficLights.valueOf(input[i]);
        }

        TrafficLights[] lightsValue = {TrafficLights.RED, TrafficLights.GREEN, TrafficLights.YELLOW};
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < lights.length; j++) {
                if (lights[j].ordinal() == 2) {
                    lights[j] = TrafficLights.RED;
                } else {
                    lights[j] = lightsValue[lights[j].ordinal() + 1];
                }
                System.out.print(lights[j].toString() + " ");
            }
            System.out.println();
        }
    }
}
