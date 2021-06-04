package reflection.blackBoxInteger;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Constructor<?>[] constructors = BlackBoxInt.class.getDeclaredConstructors();

        BlackBoxInt blackBoxInt = null;

        for (Constructor constructor : constructors) {
            if (constructor.getParameterCount() == 0) {
                try {
                    constructor.setAccessible(true);
                    blackBoxInt = (BlackBoxInt) constructor.newInstance();
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                    System.out.println(e.getMessage());
                }
                break;
            }
        }

        Method[] methods = BlackBoxInt.class.getDeclaredMethods();
        Field innerValue = null;
        try {
            innerValue = BlackBoxInt.class.getDeclaredField("innerValue");
            innerValue.setAccessible(true);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        Scanner scanner = new Scanner(System.in);
        String input;
        while (!"END".equals(input = scanner.nextLine())) {
            String[] data = input.split("_");
            String name = data[0];
            int param = Integer.parseInt(data[1]);

            Method method = Arrays.stream(methods)
                    .filter(m -> m.getName().equals(name))
                    .findFirst()
                    .orElse(null);

            if (method != null) {
                try {
                    method.setAccessible(true);
                    method.invoke(blackBoxInt, param);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }

            if (innerValue != null) {
                try {
                    System.out.println(innerValue.get(blackBoxInt));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}