package helloJPA;




import helloJPA.Member.Child;
import helloJPA.Member.MemberEx;
import helloJPA.Member.Parent;
import helloJPA.Member.TeamEx;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main4_cascade {
    public static void main(String[] args) {
        EntityManagerFactory test = Persistence.createEntityManagerFactory("test");
        EntityManager entityManager = test.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try{

            Parent parent = new Parent();
            parent.setName("부모1");
            Child child1 = new Child();
            Child child2 = new Child();
            child1.setName("자식1");
            child2.setName("자식2");
            parent.addChildList(child1);
            parent.addChildList(child2);

            /**
             * cascade = CascadeType.ALL 설정 시
             * 부모만 영속성컨텍스트로 넣어도
             * 자식도 함께 저장됨
             */
            entityManager.persist(parent);
            /**
             * orphanRemoval = true 로 설정 시
             * 부모에서 삭제되면
             * 자식도 삭제 됨 delete쿼리문이 같이 나감
             */
            parent.getChildList().remove(0);
//            entityManager.persist(child1);
//            entityManager.persist(child2);



            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
        } finally {
            entityManager.close();
            test.close();
        }




    }
}