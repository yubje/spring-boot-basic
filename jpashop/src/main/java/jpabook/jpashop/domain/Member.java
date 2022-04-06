package jpabook.jpashop.domain;


import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="member_id") // column name 지정
    private Long id;

    private String name;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member") // 연관관계의 주인이 아니며 읽기 전용임을 표기
    private List<Order> orders = new ArrayList<>();

}
