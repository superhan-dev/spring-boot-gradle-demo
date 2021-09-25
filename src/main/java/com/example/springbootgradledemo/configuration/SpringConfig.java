package com.example.springbootgradledemo.configuration;

import com.example.springbootgradledemo.aop.TimeTraceAop;
import com.example.springbootgradledemo.repository.JpaMemberRepository;
import com.example.springbootgradledemo.repository.MemberRepository;
import com.example.springbootgradledemo.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Configuration
@RequiredArgsConstructor
public class SpringConfig {
    private final MemberRepository memberRepository;

    // RequiredArgsConstructor를 사용하여 DI를 설정하기 위헤 @PersistenceContext 어노테이션이 필요하다.
    //
//    @PersistenceContext
//    private EntityManager entityManager;

//    private DataSource dataSource;

//    public SpringConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository);
    }

//    @Bean
//    public TimeTraceAop timeTraceAop(){
//        return new TimeTraceAop();
//    }

//    @Bean
//    public MemberRepository memberRepository(){
//        return new MemoryMemberRepository();
//        return new JdbcTemplateMemberRepository(dataSource);
//        return new JpaMemberRepository(entityManager);
//    }
}
