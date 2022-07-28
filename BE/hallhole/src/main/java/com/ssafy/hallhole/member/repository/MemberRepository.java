package com.ssafy.hallhole.member.repository;

import com.ssafy.hallhole.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Long> {

    Member findByEmail(String email);

    Boolean findByIdTag(String tag);
}
