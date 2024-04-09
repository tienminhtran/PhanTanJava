import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class main {
    public static void main(String[] args) {

        EntityManager em = Persistence.createEntityManagerFactory("ex5").createEntityManager();
        EntityTransaction et = em.getTransaction();

        try {
            et.begin();



            et.commit();
        } catch (Exception e) {
            et.rollback();
            e.printStackTrace();
        } finally {
            em.close();
//            emf.close();
        }
    }
}
