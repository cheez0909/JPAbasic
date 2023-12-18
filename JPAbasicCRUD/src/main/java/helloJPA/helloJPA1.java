/**
 * 1. 작성자 : Dani
 * 2. 작성일 : 12월 11일
 * 3. 처음으로 시작하는 JPA
 *  1차 캐시 조회
 */
package helloJPA;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class helloJPA1 {
    public static void main(String[] args) {
        EntityManagerFactory hello = Persistence.createEntityManagerFactory("hello");
        EntityManager entityManager = hello.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        try {

            Member member = new Member(3L, "gggg");


            // 저장 : 영속 상태(영속성 컨텍스트를 통해 관리가 됨 / DB저장X )
            // 1차 캐시에 저장
            entityManager.persist(member);
            System.out.println("========================");
            // 1차 캐시에서 불러오므로 select문 XXX
            Member member1 = entityManager.find(Member.class, 3L);
            System.out.println("===========find이후============");
            transaction.commit(); // 커밋 시점에 DB에 저장이 됨
        } catch (Exception e){
            transaction.rollback();
        } finally {
            entityManager.close();
            hello.close();
        }

    }
}
