import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class App {
    private static final String GRINGOTTS_PU = "gringotts_db";
    private static final String SALES_PU = "sales_db";
    private static final String UNIVERSITY_PU = "university_db";
    private static final String HOSPITAL_PU = "hospital_db";
    private static final String BILLS_PAYMENT_PU = "bills_payment_db";

    public static void main(String[] args) {
/*
1. Drag your database of choice from "databases" into "entities".
2. Change persistence-unit and the database name in "persistence.xml" file.
3. Test
4. Drag back database package into "databases".
5. Repeat if needed.
 */
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory(GRINGOTTS_PU);

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Engine engine = new Engine(entityManager);

        engine.run();
    }
}
