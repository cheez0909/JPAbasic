package Jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Member extends BaseEntity{

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MEMBER_ID")
    private Long id;
    private String name;
    private String city;
    private String street;
    private String zipcode;

    /**
     * 양방향 매핑 / 좋은 설계는 아님..
     * 주문내역에서 회원이 궁금할 경우 member로 조인되어 있기 때문에 충분히 조회 가능함
     */
    @OneToMany(mappedBy = "member")
    private List<Order> orderList = new ArrayList<>();
}
