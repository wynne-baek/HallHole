package com.ssafy.hallhole.member.repository;

import com.ssafy.hallhole.member.domain.Member;

import java.util.List;

public interface MemberRepository {

    void save(Member member);

    Member findOne(Long id);

    List<Member> findAll();

    List<Member> findByEmail(String email);

}
