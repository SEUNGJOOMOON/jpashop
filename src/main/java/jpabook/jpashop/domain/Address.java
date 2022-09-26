package jpabook.jpashop.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable //JPA 내장객체(셋터는 제거하고 생성자를 통해 데이터를 넣게 해야한다.)
// public 또는 protected만 사용해야 함(jpa 스펙)
@Getter
public class Address {

    private String city;
    private String street;
    private String zipcode;

    protected Address(){

    }

    public Address(String city, String street, String zipcode){
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }

}
