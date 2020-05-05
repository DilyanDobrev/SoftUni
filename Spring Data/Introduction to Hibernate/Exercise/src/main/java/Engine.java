import entities.*;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

public class Engine implements Runnable {
    private final EntityManager entityManager;
    private final BufferedReader reader;

    public Engine(EntityManager entityManager) {
        this.entityManager = entityManager;
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void run() {
        // Task 2
        //this.removeObjectTask();

        //Task 3
//        try {
//            this.containsEmployeeTask();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        //Task 4
        //this.empWithSalaryOver50K();

        //Task 5
        //this.employeesFromDepartmentTask();

        //Task 6
//        try {
//            this.addingNewAddressAndUpdateEmpl();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        //Task 7
        // this.addressesWithEmployeeCount();

        //Task 8
//        try {
//            this.getEmployeeWithProject();
//        } catch (IOException | NoResultException | NumberFormatException e) {
//            System.out.println("Enter valid ID.");
//        }

        //Task 9
        //this.findLatestTenProjects();

        //Task 10
        //this.increaseSalaries();

        //Task 11
//        try {
//            this.removeTownsTask();
//        } catch (IOException | NoResultException e) {
//            System.out.println("Invalid town name");
//        }

        //Task 12
//        try {
//            this.findEmployeesByName();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        //Task 13
        //this.employeesMaximumSalaries();
    }

    private void employeesMaximumSalaries() {
        this.entityManager.createQuery(
                "SELECT d FROM Department AS d " +
                        "JOIN d.employees AS e " +
                        "GROUP BY d " +
                        "HAVING MAX(e.salary) NOT BETWEEN 30000 AND 70000", Department.class)
                .getResultStream()
                .forEach(d -> System.out.printf("%s %.2f%n",
                        d.getName(),
                        d.getEmployees()
                                .stream()
                                .sorted((e1, e2) -> e2.getSalary().compareTo(e1.getSalary()))
                                .limit(1)
                                .map(Employee::getSalary)
                                .findFirst().orElse(null)));
    }

    private void findEmployeesByName() throws IOException {
        System.out.println("Enter pattern.");
        String startsWith = reader.readLine();

        this.entityManager.createQuery("SELECT e FROM Employee AS e " +
                "WHERE e.firstName LIKE :pattern", Employee.class)
                .setParameter("pattern", startsWith + "%")
                .getResultStream()
                .forEach(e -> System.out.printf(
                        "%s %s - %s - ($%.2f)%n",
                        e.getFirstName(),
                        e.getLastName(),
                        e.getJobTitle(),
                        e.getSalary()));
    }

    private void removeTownsTask() throws IOException {
        System.out.println("Enter town name.");
        String townName = reader.readLine();

        Town town = this.entityManager.createQuery("SELECT t FROM Town AS t " +
                "WHERE t.name = :name", Town.class)
                .setParameter("name", townName)
                .getSingleResult();

        List<Address> addresses = this.entityManager.createQuery("SELECT a FROM Address AS a " +
                "WHERE a.town.name = :town", Address.class)
                .setParameter("town", townName)
                .getResultList();

        this.entityManager.getTransaction().begin();

        for (Address address : addresses) {
            for (Employee employee : address.getEmployees()) {
                employee.setAddress(null);
            }
            this.entityManager.remove(address);
        }

        this.entityManager.remove(town);

        this.entityManager.getTransaction().commit();
        this.entityManager.close();

        if (addresses.size() == 1) {
            System.out.printf("%d address in %s deleted%n",
                    addresses.size(), town.getName());
        } else {
            System.out.printf("%d addresses in %s deleted%n",
                    addresses.size(), town.getName());
        }
    }

    private void increaseSalaries() {
        List<Employee> employees = this.entityManager
                .createQuery("SELECT e FROM Employee AS e " +
                                "WHERE e.department.name " +
                                "IN('Engineering', 'Tool Design', 'Marketing ', 'Information Services')",
                        Employee.class)
                .getResultList();

        this.entityManager.getTransaction().begin();
        employees.forEach(e -> {
            this.entityManager.detach(e);
            e.setSalary(e.getSalary().multiply(BigDecimal.valueOf(1.12)));
            this.entityManager.merge(e);
            this.entityManager.flush();
            System.out.printf("%s %s ($%.2f)%n",
                    e.getFirstName(),
                    e.getLastName(),
                    e.getSalary());
        });

        this.entityManager.getTransaction().commit();
    }

    private void findLatestTenProjects() {
        this.entityManager.createQuery("SELECT p FROM Project AS p " +
                "ORDER BY p.startDate DESC", Project.class)
                .setMaxResults(10)
                .getResultStream()
                .sorted(Comparator.comparing(Project::getName))
                .forEach(p -> System.out.printf(
                        "Project name: %s%n" +
                                "\tProject Description: %s%n" +
                                "\tProject Start Date: %s%n" +
                                "\tProject End Date: %s%n",
                        p.getName(),
                        p.getDescription(),
                        p.getStartDate(),
                        p.getEndDate()));
    }

    private void getEmployeeWithProject() throws IOException {
        System.out.println("Enter ID.");
        int id = Integer.parseInt(reader.readLine());

        Employee employee = this.entityManager.createQuery("SELECT e FROM Employee AS e " +
                "WHERE e.id = :id", Employee.class)
                .setParameter("id", id)
                .getSingleResult();

        System.out.printf("%s %s - %s%n",
                employee.getFirstName(),
                employee.getLastName(),
                employee.getJobTitle());

        employee.getProjects()
                .stream()
                .sorted(Comparator.comparing(Project::getName))
                .forEach(p -> System.out.println("\t" + p.getName()));
    }

    private void addressesWithEmployeeCount() {
        this.entityManager.createQuery("SELECT a FROM Address AS a " +
                "ORDER BY a.employees.size DESC", Address.class)
                .setMaxResults(10)
                .getResultStream()
                .forEach(a -> System.out.printf("%s - %d employees%n",
                        a.getText(),
                        a.getEmployees().size()));
    }

    private void addingNewAddressAndUpdateEmpl() throws IOException {
        System.out.println("Enter last name.");
        String lastName = reader.readLine();

        Employee employee;
        try {
            employee = this.entityManager.createQuery("SELECT e FROM Employee as e " +
                    "WHERE e.lastName = :name", Employee.class)
                    .setParameter("name", lastName)
                    .getSingleResult();
        } catch (NoResultException | NonUniqueResultException e) {
            System.out.println("Employee does not exist or non unique last name.");
            return;
        }

        Address address = createNewAddress("Vitoshka 15");

        this.entityManager.getTransaction().begin();
        this.entityManager.detach(employee);
        employee.setAddress(address);
        this.entityManager.merge(employee);
        this.entityManager.getTransaction().commit();
    }

    private Address createNewAddress(String newAddress) {
        Address address = new Address();
        address.setText(newAddress);

        this.entityManager.getTransaction().begin();
        this.entityManager.persist(address);
        this.entityManager.getTransaction().commit();

        return address;
    }

    private void employeesFromDepartmentTask() {
        this.entityManager.createQuery("SELECT e FROM Employee as e " +
                "WHERE e.department.name = 'Research and Development' " +
                "ORDER BY e.salary, e.id", Employee.class)
                .getResultStream()
                .forEach(e -> System.out.printf("%s %s from %s - $%.2f%n",
                        e.getFirstName(),
                        e.getLastName(),
                        e.getDepartment().getName(),
                        e.getSalary()));
    }

    private void empWithSalaryOver50K() {
        this.entityManager.createQuery("SELECT e FROM Employee as e " +
                "WHERE e.salary > 50000", Employee.class)
                .getResultStream()
                .forEach(e -> System.out.printf("%s%n", e.getFirstName()));
    }

    private void containsEmployeeTask() throws IOException {
        System.out.println("Enter full name.");
        String fullName = reader.readLine();

        try {
            this.entityManager.createQuery("SELECT e FROM Employee AS e " +
                    "WHERE CONCAT(e.firstName, ' ', e.lastName) = :name", Employee.class)
                    .setParameter("name", fullName)
                    .getSingleResult();

            System.out.println("Yes");
        } catch (NoResultException e) {
            System.out.println("No");
        }
    }

    private void removeObjectTask() {
        String query = "UPDATE Town t SET t.name = LOWER(t.name) WHERE length(t.name) <= 5";

        this.entityManager.getTransaction().begin();
        this.entityManager.createQuery(query).executeUpdate();
        this.entityManager.getTransaction().commit();
        this.entityManager.close();
    }
}
