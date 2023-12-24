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
public class Book extends Item{
    @Id @GeneratedValue
    @Column(name = "BOOK_ID")
    private Long id;

    private String author;
    private String isbn;
}
