package Jpashop.domain;

import javax.persistence.*;

@Entity
public class DELIVERY {
    @Id @GeneratedValue
    @Column(name = "DELIVERY_ID")
    private Long id;

    private String city;
    private String street;
    private String zipcode;
    private DelivertStatus delivertStatusstatus;

    // @OneToOne(mappedBy = "ORDER_ID")
    /**
     * mappedBy는 주인이 설정한 필드명과 동일해야함
     */
    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    private Order order;
}
