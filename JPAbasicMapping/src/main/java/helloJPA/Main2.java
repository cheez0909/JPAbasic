package helloJPA;




import helloJPA.Member.MemberEx;
import helloJPA.Member.TeamEx;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class Main2 {
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
            /**
             * 연관관계 편의 메소드를 사용하자!
             */
            // memberEx.updateTeamEx(teamEx);
            entityManager.persist(memberEx);
            teamEx.addMember(memberEx);

            /**
             * 역방향에도 값을 입력해야 한다.
             */
            // teamEx.getMemberExList().add(memberEx);

            /*
            entityManager.flush();
            entityManager.clear();
            */

            /**
             * 영속성 컨텍스트를 초기화 시키지 않았기 때문에 1차 캐시에서 찾아온다.
             * 메모리에만 올라와 있는 경우
             * 이 경우엔 리스트가 조회되지 않는다.
             * 따라서 역방향에 입력해줘야 한다.
             */
            TeamEx teamEx1 = entityManager.find(TeamEx.class, teamEx.getId());
            List<MemberEx> memberExList = teamEx1.getMemberExList();
            System.out.println("======================");
            for(MemberEx m : memberExList){
                System.out.println(m.getUsername());
            }
            System.out.println("======================");

            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
        } finally {
            entityManager.close();
            test.close();
        }




    }
}