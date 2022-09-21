package jpabook.jpashop.domain;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Order;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue // 시퀀스 자동증가
    @Column(name = "member_id")
    private Long id;

    private String username;

    @Embedded // JPA 내장객체를 사용
    private Address address;

    @OneToMany(mappedBy = "member") // 하나의 회원이 여러개의 상품을 주문, member에 의해 mapping(읽기 전용)
    private List<Order> orders = new ArrayList<>();
}
