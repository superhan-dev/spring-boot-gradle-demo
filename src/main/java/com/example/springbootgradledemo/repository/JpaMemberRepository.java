package com.example.springbootgradledemo.repository;

import com.example.springbootgradledemo.domain.Member;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class JpaMemberRepository implements MemberRepository{
    private final EntityManager entityManager;

    @Override
    public Member save(Member member) {
        entityManager.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = entityManager.find(Member.class, id);
        return Optional.of(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = entityManager.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
         return entityManager.createQuery("select m from Member m",Member.class)
                 .getResultList();
    }
}
