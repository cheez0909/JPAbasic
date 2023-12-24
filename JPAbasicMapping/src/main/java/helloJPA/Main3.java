package helloJPA;




import helloJPA.Member.MemberEx;
import helloJPA.Member.TeamEx;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class Main3 {
    public static void main(String[] args) {
        EntityManagerFactory test = Persistence.createEntityManagerFactory("test");
        EntityManager entityManager = test.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try{


            TeamEx teamEx = new TeamEx();
            teamEx.setName("TEAM_A");


            MemberEx memberEx = new MemberEx();
            memberEx.setUsername("홍길동");
            memberEx.setTeamEx(teamEx);
            teamEx.addMember(memberEx);

            entityManager.persist(memberEx);
            entityManager.persist(teamEx);

            entityManager.flush();
            entityManager.clear();

            MemberEx memberEx1 = entityManager.find(MemberEx.class, memberEx.getId());
            System.out.println(memberEx1.getClass()); // member 테이블만 조회하는 쿼리가 나감
            System.out.println(memberEx1.getTeamEx().getClass()); // team은 프록시로 가져옴

            System.out.println("==================================");
            System.out.println(memberEx1.getTeamEx().getName()); // 실제 초기화때 쿼리문이 나감



            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
        } finally {
            entityManager.close();
            test.close();
        }




    }
}