package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository // spring bean으로 자동등록(컴포넌트 스캔 대상)
public class MemberRepository {

    @PersistenceContext
    private EntityManager em; // 스프링이 EntityManager 을 만들어서 injection 해준다.

    public void save(Member member){
        em.persist(member); // pk를 키값으로 리턴해줌.
    }

    public Member findOne(Long id){
        return em.find(Member.class, id);
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m" , Member.class).getResultList();
        // jpql -> 기능적으론 쿼리랑 거의 동일하다
        // sql은 테이블 대상으로 쿼리를 하지만, jpql은 엔티티에 대한 쿼리를 수행
    }

    public List<Member> findByName(String name){
        return em.createQuery("select m from Member m where m.username = :name", Member.class)
                .setParameter("name", name).getResultList();
    }



}
