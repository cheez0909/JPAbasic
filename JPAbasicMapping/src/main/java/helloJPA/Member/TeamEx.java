package helloJPA.Member;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class TeamEx {


    @Id @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;
    @Setter
    private String name;

    // 양방향 매핑
    // mappedBy = "변수명" -> 연관관계를 갖고 있는 클래스의 변수명
    // 연관관계의 주인이 아님 / 읽기만 가능함

    @Setter
    @OneToMany(mappedBy = "teamEx")
    private List<MemberEx> memberExList = new ArrayList<>(); // add할 때, NPE가 뜨지 않음

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<MemberEx> getMemberExList() {
        return memberExList;
    }

    /**
     * 연관관계 메소드
     */
    public void addMember(MemberEx memberEx){
        memberEx.setTeamEx(this);
        memberExList.add(memberEx);
    }

}
