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
public class Movie extends Item{
    @Id @GeneratedValue
    @Column(name = "MOVIE_ID")
    private Long id;

    private String director;
    private String actor;

}
