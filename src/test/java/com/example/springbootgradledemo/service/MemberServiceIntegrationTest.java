package com.example.springbootgradledemo.service;

import com.example.springbootgradledemo.domain.Member;
import com.example.springbootgradledemo.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * 테스트 때는 Transactional을 이용하여 테스트 시작전에 트랜젝션을 시작하고 테스트 완료후에 항상 롤백을 실행한다.
 * 이렇게 하면 테스트 데이터는 데이터베이스에 남지 않으므로 다음 테스트를 진행할 수 있게된다.
 */

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {
    @Autowired MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    void join(){
        // given
        Member member = new Member();
        member.setName("spring");

        // when
        Long saveId = memberService.join(member);

        // then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void duplicateJoin(){
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        // then
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }
}
