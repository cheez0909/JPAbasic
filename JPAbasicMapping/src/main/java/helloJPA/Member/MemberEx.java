package helloJPA.Member;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
// @SequenceGenerator(name="member_seq_generator"
//        , sequenceName = "member_seq")
//@Table(uniqueConstraints = ) 유니크 제약조건을 걸 수 있음
public class MemberEx {

    /**
     * 회원은 일반 회원과 관리자로 구분
     * 회원 가입일과 수정일
     * 회원을 설명할 수 있는 필드 (제한X)
     */

    @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY) // -> 데이터베이스에 위임
    // @GeneratedValue(strategy = GenerationType.SEQUENCE
    //        , generator = "member_seq_generator")
    @GeneratedValue
    private Long id;

    // 객체엔 username db엔 name으로 필드명 저장
    // updatable = false일때 변경 X
    // nullabe =false notnull제약조건
    // columnDefinition = "varchar(100) default 'EMPTY'"
    @Setter
    @Column(name="name")
    private String username;

    // @Column(name="TEAM_ID")
    // private Long teamId;

    /**
     * 연관관계를 매핑하자!
     * 연관관계의 주인임
     * 등록과 수정이 가능함
     */
    // 관계 매핑
    // 조인컬럼
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TEAM_ID")
    @Setter
    private TeamEx teamEx;

    // DB에서 integer와 비슷한 숫자 타입이 생성됨
    // private Integer age;

    // enum클래스 일 때
    // db에는 enum타입이 없음
    // 따라서 어노테이션 설정해줘야함
    // ORDINAL 은 enum의 순서를 저장하는데, 이때 enum클래스에 새로운 필드가 추가될 경우
    // 순서에 혼동이 올 수 있다. String사용을 권장함
    // @Enumerated(EnumType.STRING)
    // private RoleType roleType;

    // temporal엔 타입이 세 종류가 있는데
    // db에는 날짜, 시간, 날짜&시간을 구분에서 사용하기 때문에
    // 매핑 정보를 줘야 한다.
    // @Temporal(TemporalType.TIMESTAMP)
    // private Date lastModifieDate;

    // varchar2를 넘는 큰 데이터를 넣고 싶을 때
    // @Lob
    // private String description;

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public TeamEx getTeamEx() {
        return teamEx;
    }

    /**
     * 역방향도 함께 입력
     */
    /*
    public void updateTeamEx(TeamEx teamEx) {
        this.teamEx = teamEx;
        teamEx.getMemberExList().add(this);
    }
     */

}
