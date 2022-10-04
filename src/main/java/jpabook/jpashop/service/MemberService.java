package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
//@Transactional // jpa에서는 모든 서비스는 트랜젝션 안에서 실행되어야함.(데이터 변경)
public class MemberService {

//    @Autowired // 필드 injection(바꿀 수 있는 방법이 없다.)
//    private MemberRepository memberRepository;

//    MemberRepository memberRepository; // setter injection 주입(직접 주입할 수 있다. / 실제 운영에서 누군가 바꿀 수 있다.)
//
//    @Autowired
//    private void setMemberRepository(MemberRepository memberRepository){
//        this.memberRepository = memberRepository;
//    }

    // 필드 injection
    private final MemberRepository memberRepository; // 생성자 injection(더 이상 변화하지 않기 때문에 final로 선언)

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 회원가입
    @Transactional
    public Long join(Member member){

        _validateDuplicateMember(member);

        memberRepository.save(member);
        return member.getId();
    }

    // 멤버 이름 중복 체크
    private void _validateDuplicateMember(Member member) {

        List<Member> members = memberRepository.findByName(member.getUsername());

        if(!members.isEmpty()){
            throw new IllegalAccessException("이미 존재하는 회원입니다.");
        }
    }

    // 회원 전체 조회
    @Transactional(readOnly = true) // 조회시에는 트랜잭션을 최적화함(읽기에는 가급적 readonly = true)
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Member findOne(Long id){
        return memberRepository.findOne(id);
    }

}
