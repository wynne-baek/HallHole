package com.ssafy.hallhole.member.repository;

import com.ssafy.hallhole.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member,Long> {

    Member findByIdTag(String tag);

    @Query(value="select * from member where email=:email and is_out=false", nativeQuery = true)
    Member findByEmail(@Param("email") String email);

    @Query(value="select * from member where email=:email", nativeQuery = true)
    List<Member> findAllByEmail(@Param("email") String email);

}
