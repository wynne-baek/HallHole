package com.ssafy.hallhole.member.service;

import com.ssafy.hallhole.member.Member;


public interface MemberService {

    void join(Member member);

    void duplicateMember(String email);

    void findPW(String email);

    void delMem(Long memberId);

    void changePW(String email, String password);

    Member changeInfo(String tag, String profile,
                    String name, String gender, String age);

    Member getInfo(String email);

    Member loginhh(String email, String password);

}
