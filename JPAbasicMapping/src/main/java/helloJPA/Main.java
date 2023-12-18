package helloJPA;




import helloJPA.Member.MemberEx;
import helloJPA.Member.RoleType;
import helloJPA.Member.TeamEx;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory test = Persistence.createEntityManagerFactory("test");
        EntityManager entityManager = test.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try{
            TeamEx teamEx = new TeamEx();
            teamEx.setName("TEAM_A");
            entityManager.persist(teamEx);

            MemberEx memberEx = new MemberEx();
            memberEx.setUsername("홍길동");
            // memberEx.setTeamId(teamEx.getId());
            // memberEx.setTeamEx(teamEx);
            entityManager.persist(memberEx);
            /**
             * DB 쿼리를 보고 싶은 경우엔 flush와 clear
             */
            entityManager.flush();
            entityManager.clear();

            MemberEx memberEx1 = entityManager.find(MemberEx.class, memberEx.getId());
            List<MemberEx> memberExList = memberEx1.getTeamEx().getMemberExList();
            memberExList.stream().forEach(System.out::println);
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
        } finally {
            entityManager.close();
            test.close();
        }




    }
}