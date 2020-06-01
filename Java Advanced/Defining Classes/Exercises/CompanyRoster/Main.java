package DefiningClassesExercises.CompanyRoster;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader =
                new BufferedReader(
                        new InputStreamReader(
                                System.in
                        )
                );

        int n = Integer.parseInt(reader.readLine());

        Map<String, Department> departments = new HashMap<>();

        while (n-- > 0) {
            String[] data = reader.readLine().split("\\s+");

            double salary = Double.parseDouble(data[1]);
            String departmentName = data[3];

            Employee emp = new Employee(data[0], salary, data[2]);

            if (data.length == 5) {
                if (Character.isDigit(data[4].charAt(0))) {
                    emp.setAge(Integer.parseInt(data[4]));
                } else {
                    emp.setEmail(data[4]);
                }
            } else if (data.length == 6) {
                emp.setEmail(data[4]);
                emp.setAge(Integer.parseInt(data[5]));
            }
            if (!departments.containsKey(departmentName)) {
                departments.put(departmentName, new Department());
            }
            departments.get(departmentName).addEmployee(emp);
        }
        departments
                .entrySet()
                .stream()
                .sorted((f, s) -> Double.compare(s.getValue().getAverageSalary(), f.getValue().getAverageSalary()))
                .limit(1)
                .forEach(entry -> {
                    System.out.println(String.format("Highest Average Salary: %s", entry.getKey()));
                    entry.getValue().getEmployee()
                            .stream()
                            .sorted((f, s) -> Double.compare(s.getSalary(), f.getSalary()))
                            .forEach(employee -> {
                                System.out.println(employee.toString());
                            });
                });
    }
}
