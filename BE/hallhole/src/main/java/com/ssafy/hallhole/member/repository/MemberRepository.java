package com.ssafy.hallhole.member.repository;

import com.ssafy.hallhole.member.domain.Member;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberRepository{


    void save(Member member);

    Member findByIdTag(String tag);

    Member findByEmail(String email);

    Member findById(Long id);

    List<Member> findAllByEmail(String email);

    Member findBySid(String kakao_sid);

    List<Member> findAllAliveMember();

    boolean existsByEmail(String email);

    List<Member> findMembersByNamePaging(int start, int size, String name);

    Long getMemberCntByName(String name);
}
