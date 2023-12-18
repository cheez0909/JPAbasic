/**
 * 1. 작성자 : Dani
 * 2. 작성일 : 12월 11일
 * 3. 처음으로 시작하는 JPA
 *  flush
 */
package helloJPA;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class helloJPA2 {
    public static void main(String[] args) {
        EntityManagerFactory hello = Persistence.createEntityManagerFactory("hello");
        EntityManager entityManager = hello.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        try {

            Member member = new Member(5L,"dddd");

            entityManager.persist(member);
            entityManager.flush(); // DB에 저장

            System.out.println("========================");

            transaction.commit(); // 커밋 시점에 DB에 저장이 됨
        } catch (Exception e){
            transaction.rollback();
        } finally {
            entityManager.close();
            hello.close();
        }

    }
}
