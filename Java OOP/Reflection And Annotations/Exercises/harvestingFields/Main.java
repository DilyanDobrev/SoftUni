package reflection.harvestingFields;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Class<?> clazz = RichSoilLand.class;
		Field[] declaredFields = clazz.getDeclaredFields();

		Scanner scanner = new Scanner(System.in);

		String input;
		while (!"HARVEST".equals(input = scanner.nextLine())) {
			for (Field field : declaredFields) {
				String modifier = Modifier.toString(field.getModifiers());
				if (modifier.equals(input) || input.equals("all")) {
					String typeName = field.getType().getSimpleName();
					String name = field.getName();
					System.out.printf("%s %s %s%n", modifier, typeName, name);
				}
			}
		}
	}
}
