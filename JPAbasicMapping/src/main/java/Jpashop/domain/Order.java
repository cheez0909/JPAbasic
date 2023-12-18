package Jpashop.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Orders")
public class Order {

    @Id @GeneratedValue
    @Column(name = "ORDER_ID")
    private Long id;
    @Column(name = "MEMBER_ID")
    private Long memberId; // 객체를 테이블에 맞추어 모델링한 것
    // memberId 값 보단 member를 가져오는 것이 효율적이지 않을까
//    private Member member;
    private LocalDateTime orderDate; // 스프링부트 환경에서는 order_date로 저장됨
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
}
