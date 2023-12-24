package Jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Album extends Item{
    @Id @GeneratedValue
    @Column(name = "ALBUN_ID")
    private Long id;

    private String artist;
}
