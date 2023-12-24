package Jpashop;


import Jpashop.domain.Item;
import Jpashop.domain.Movie;
import Jpashop.domain.Order;
import Jpashop.domain.OrderItem;
import org.hibernate.Hibernate;

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

            Movie movie = new Movie();
            movie.setActor("정우성");
            movie.setDirector("김성수");
            movie.setName("서울의 봄");
            movie.setPrice(11000);
            entityManager.persist(movie);

            entityManager.flush();
            entityManager.clear();

            /**
             * 실제 사용이 될 때 select문이 호출된다
             * getClass -> 초기화 전
             * getName -> 초기화가 됨
             */
            /*
            Item item = entityManager.getReference(Movie.class, movie.getId());
            System.out.println("==============================================");
            System.out.println(item.getClass());
            System.out.println("==============================================");
            System.out.println(item.getName());
            System.out.println("==============================================");
            System.out.println(item.getName());
            */

            /**
             * 이미 엔티티가 호출 된 경우
             * 프록시 객체가 아닌 실제 엔티티가 호출된다.
             * "==" 비교시 true를 보장해줌
             */
            /*
            Movie movie1 = entityManager.find(Movie.class, movie.getId());
            System.out.println(movie1.getClass());
            Movie reference = entityManager.getReference(Movie.class, movie.getId());
            System.out.println(reference.getClass());
            System.out.println(reference == movie1);
             */

            /*
            Movie reference = entityManager.getReference(Movie.class, movie.getId());
            System.out.println(reference.getClass());

            Movie movie1 = entityManager.find(Movie.class, movie.getId());
            System.out.println(movie1.getClass());

            System.out.println(reference instanceof Movie);
             */

             Movie reference = entityManager.getReference(Movie.class, movie.getId());
             System.out.println(reference.getClass());
            System.out.println(test.getPersistenceUnitUtil().isLoaded(reference)); // 프록시의 초기화 여부
            Hibernate.initialize(reference); // 하이버네이트가 지원하는 강제 초기화, JPA엔 없음
            System.out.println(test.getPersistenceUnitUtil().isLoaded(reference)); // 프록시의 초기화 여부

            /**
             * 프록시가 준영속상태일 때,
             */
            // entityManager.detach(reference);

            /**
             * 초기화 시 에러가 발생함
             */
            // String name = reference.getName();
            // System.out.println(name);


//            Order order = new Order();
//            order.addOrderItem(new OrderItem());

            /**
             * 양방향 연관관계가 아닐 경우
             * 따로따로 영속성 컨텍스트에 저장해주어도 무방하다.
             * 연관관계의 주인이 아닌 것은 조회밖에 할 수 없음
             */
            /*
            Order order1 = new Order();
            entityManager.persist(order1);

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order1);
            entityManager.persist(orderItem);
             */
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
            e.printStackTrace();
        } finally {
            entityManager.close();
            test.close();
        }
    }
}
