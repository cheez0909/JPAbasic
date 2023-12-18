package Jpashop;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpashopMain {
    public static void main(String[] args) {
        EntityManagerFactory test = Persistence.createEntityManagerFactory("test");
        EntityManager entityManager = test.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try{

            transaction.commit();

        }catch (Exception e){
            transaction.rollback();
        } finally {
            entityManager.close();
            test.close();
        }
    }
}
