package jpabook.jpashop.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable //JPA 내장객체
@Getter
public class Address {

    private String city;

    private String street;

    private String zipcode;


}
