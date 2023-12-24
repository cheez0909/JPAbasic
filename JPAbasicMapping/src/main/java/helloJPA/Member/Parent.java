package helloJPA.Member;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Parent {

    @Id @GeneratedValue
    @Column(name = "PARENT_ID")
    private Long id;
    private String name;
    private int age;

    /**
     * cascade = CascadeType.ALL
     * 설정 시 부모를 영속성컨텍스트로 저장하면
     * 자식들도 한꺼번에 저장됨
     */
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Child> childList = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Child> getChildList() {
        return childList;
    }

    public void addChildList(Child child) {
        childList.add(child);
        child.setParent(this);
    }
}
