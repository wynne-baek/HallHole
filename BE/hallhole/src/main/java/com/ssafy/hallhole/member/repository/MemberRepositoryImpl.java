package com.ssafy.hallhole.member.repository;

import com.ssafy.hallhole.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepository {

    private final EntityManager em;

    @Override
    public void save(Member member) { em.persist(member); }

    @Override
    public Member findByIdTag(String tag) {
        return em.createQuery("select m from Member m where m.idTag = :tag",Member.class)
                .setParameter("tag",tag)
                .getSingleResult();
    }

    @Override
    public Member findByEmail(String email){
        return em.createQuery("select m from Member m where m.email=:email and m.isOut=false",Member.class)
                .setParameter("email",email)
                .getSingleResult();
    }
    @Override
    public Member findById(Long id){
        return em.createQuery("select m from Member m where m.id=:id and m.isOut=false",Member.class)
                .setParameter("id",id)
                .getSingleResult();
    }
    @Override
    public List<Member> findAllByEmail(String email){
        return em.createQuery("select m from Member m where m.email=:email and m.isOut=false",Member.class)
                .setParameter("email",email)
                .getResultList();
    }
    @Override
    public Member findBySid(String kakao_sid){
        return em.createQuery("select m from Member m where m.kakaoSid=:kakao_sid and m.isOut=false",Member.class)
                .setParameter("kakao_sid",kakao_sid)
                .getSingleResult();
    }
    @Override
    public List<Member> findAllAliveMember(){
        return em.createQuery("select m from Member m where m.isOut=false",Member.class)
                .getResultList();
    }
    @Override
    public boolean existsByEmail(String email) {
        if(em.createQuery("select count(m.id) from Member m where m.email=:email",Long.class)
                .setParameter("email",email).getSingleResult()!=0) return true;
        return false;
    }
    @Override
    public List<Member> findMembersByNamePaging(int start, int size, String name){
        return em.createQuery("select m from Member m order by m.followerCnt desc ",Member.class)
                .setFirstResult(start)
                .setMaxResults(size)
                .getResultList();
    }

    @Override
    public Long getMemberCntByName(String name) {
        return em.createQuery("select count(m) from Member m where m.name like :name ",Long.class)
                .setParameter("name","%"+name+"%")
                .getSingleResult();
    }

}
