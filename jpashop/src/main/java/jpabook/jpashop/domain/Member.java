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

    private Address address;

    private List<Order> orders = new ArrayList<>();

}
