package jpabook.jpashop;

import jpabook.jpashop.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository //컴포넌트 스캔 대상으로 자동으로 bean에 등록됨.
public class MemberRepository {

    @PersistenceContext
    private EntityManager em; // jpa 설정으로 자동으로 등록됨.

    public Long save(Member member){
        em.persist(member);
        return member.getId();
    }

    public Member find(Long id){
        return em.find(Member.class, id);
    }

}
