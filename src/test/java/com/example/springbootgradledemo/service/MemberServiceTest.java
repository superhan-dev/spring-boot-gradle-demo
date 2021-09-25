package com.example.springbootgradledemo.service;

import com.example.springbootgradledemo.domain.Member;
import com.example.springbootgradledemo.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * 통합테스트가 있음에도 단위테스트를 구현하는 이유는 테스트 속도를 향상시켜준다.
 * 순수한 단위테스트를 쪼개서 테스트를 구현하고 스프링 컨테이너 없이 테스트를 구현할 수 있게되면
 * 테스트를 짜는 기량을 향상시킬 수 있다.
 */
class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository repository;

    @BeforeEach
    public void beforeEach(){
        // DI를 실행한다.
        repository = new MemoryMemberRepository();
        memberService = new MemberService(repository);
    }

    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    // TIP: 테스트는 given, when, then으로 구문을 구분하여 구현하면 가시성이 높다.

    @Test
    void join() {
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
/*
        try {
            memberService.join(member2);
            fail();
        } catch (IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }
*/

        // then
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}