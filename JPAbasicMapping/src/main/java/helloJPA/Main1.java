package helloJPA;




import helloJPA.Member.MemberEx;
import helloJPA.Member.TeamEx;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class Main1 {
    public static void main(String[] args) {
        EntityManagerFactory test = Persistence.createEntityManagerFactory("test");
        EntityManager entityManager = test.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try{
            /**
             * 연관관계 주인에만 값을 입력, 역방향엔 입력하지 않은 경우
             */
            TeamEx teamEx = new TeamEx();
            teamEx.setName("TEAM_A");
            entityManager.persist(teamEx);

            MemberEx memberEx = new MemberEx();
            memberEx.setUsername("홍길동");
            // memberEx.setTeamEx(teamEx);
            entityManager.persist(memberEx);

            /**
             * DB 쿼리를 보고 싶은 경우엔 flush와 clear
             */
            entityManager.flush();
            entityManager.clear();

            /**
             * 영속성 컨텍스트를 초기화 시킨 후 DB에서 값을 찾아오기 때문에 select
             * 또한 DB에 값이 저장됐기 때문에 역방향에 값을 입력하지 않았어도 List 조회가 가능하다.
             */
            TeamEx teamEx1 = entityManager.find(TeamEx.class, teamEx.getId());
            List<MemberEx> memberExList = teamEx1.getMemberExList();
            for(MemberEx m : memberExList){
                System.out.println(m.getUsername());
            }

            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
        } finally {
            entityManager.close();
            test.close();
        }




    }
}