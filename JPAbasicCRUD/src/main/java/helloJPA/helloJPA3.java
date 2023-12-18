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

public class helloJPA3 {
    public static void main(String[] args) {
        EntityManagerFactory hello = Persistence.createEntityManagerFactory("hello");
        EntityManager entityManager = hello.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        try {

            Member member = entityManager.find(Member.class, 5L);
            member.setName("(수정)정조");

//            entityManager.detach(member); // 준영속상태로 만듦
            entityManager.clear(); // 영속성 컨텍스트를 전체 초기화함
            Member member2 = entityManager.find(Member.class, 5L);

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
