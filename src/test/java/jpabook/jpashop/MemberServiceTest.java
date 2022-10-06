package jpabook.jpashop;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional // test rollback 수행(test case에 있을 시)
public class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;
    
    @Autowired
    EntityManager em;

    @Test
    //@Rollback(false) //rollback 안하고 commit 수행
    public void 회원가입() throws Exception{

        Member member = new Member();
        member.setUsername("kim");

        Long savedId = memberService.join(member);

        //em.flush(); // 실제 쿼리 insert 수행
        assertEquals(member, memberRepository.find(savedId));

    }

    @Test(expected = IllegalStateException.class) // 해당 exception이 잡힐경우 테스트 성공
    public void 중복회원() throws Exception{

        Member member1 = new Member();
        member1.setUsername("kim1");

        Member member2 = new Member();
        member1.setUsername("kim1");


        memberService.join(member1);
//        try{
//            memberService.join(member2); // 예외가 발생한다.
//        }catch(IllegalStateException e){
//            return;
//        }

        memberService.join(member2); // 예외가 발생한다.

        fail("실패해야한다."); // 실행되면 안된다(이미 예외가 발생했어야함)
    }

}
