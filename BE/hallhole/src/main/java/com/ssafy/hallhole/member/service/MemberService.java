package com.ssafy.hallhole.member.service;

import com.ssafy.hallhole.member.domain.Member;


public interface MemberService {

    Long join(Member member);

    void duplicateMember(Member member);

//    void findPW


}
