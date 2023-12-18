/**
 * 1. 작성자 : Dani
 * 2. 작성일 : 12월 11일
 * 3. 처음으로 시작하는 JPA
 */
package helloJPA;

import org.jpabasic.Main;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class helloJPA {
    public static void main(String[] args) {
        // EntityManagerFactory는 처음에 한 번만 생성한다.
        EntityManagerFactory hello = Persistence.createEntityManagerFactory("hello");

        // EntityManager는 필요할 때마다 생성
        EntityManager entityManager = hello.createEntityManager();
        // 모든 데이터의 변경은 트랜잭션 안에서 일어나야 함.
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            // 멤버 생성 : 비영속 상태
        Member member = new Member(4L, "이순신");

            // 저장 : 영속 상태(영속성 컨텍스트를 통해 관리가 됨 / DB저장X )
             entityManager.persist(member);

            // 멤버 수정 : 조회 후 set
            // 수정의 경우 또 다시 persist로 저장할 필요가 없다
            // JPA에서 커밋하기 전에 데이터의 변경이 감지되면 update문을 날림.
        /*
        Member member = entityManager.find(Member.class, 1L);
        member.setName("(수정)유관순");
         */

            // 멤버 삭제
        /*
        Member member = entityManager.find(Member.class, 2L);
        entityManager.remove(member);
         */
//            List<Member> resultList = entityManager.createQuery("select m from Member as m", Member.class)
//                    .setFirstResult(5).setMaxResults(10).getResultList();
            /**
             * entityManager.setFirstResult(4).setMaxResults(10) -> 페이징 할 때 자동으로 반영됨
             */
//            String string = resultList.stream().toString();
//            System.out.println(string);

            transaction.commit(); // 커밋 시점에 DB에 저장이 됨
        } catch (Exception e){
            transaction.rollback();
        } finally {
            entityManager.close();
            hello.close();
        }

    }
}
