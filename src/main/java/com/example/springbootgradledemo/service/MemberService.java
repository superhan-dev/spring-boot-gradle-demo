package com.example.springbootgradledemo.service;

import com.example.springbootgradledemo.domain.Member;
import com.example.springbootgradledemo.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

//@Service
//@RequiredArgsConstructor

/**
 * jpa는 모든 변경이 Transaction 안에서 수행되어야 하기 때문에 service에 @Transactional 어노테이션을 달아준다.
 */
@Transactional
public class MemberService {
    private MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }


    public Long join(Member member){
        validateDuplicateMember(member);

        memberRepository.save(member);
        return member.getId();

    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
            .ifPresent(m -> {
                throw new IllegalStateException("이미 존재하는 회원입니다.");
            });
    }

    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long id){
        return memberRepository.findById(id);
    }
}
