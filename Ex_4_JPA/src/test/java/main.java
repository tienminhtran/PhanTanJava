import entity.Course;
import entity.Department;
import entity.Instructor;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.LocalDate;

public class main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ex4");
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();

        try {
            et.begin();


//            Department department = new Department(123,4.54, 1, "IT", LocalDate.now());
//            em.persist(department);
//
//            Course course = new Course(1, 4, "Java", department);
//            em.persist(course);

            et.commit();
        } catch (Exception e) {
            et.rollback();
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}
