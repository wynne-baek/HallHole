package com.ssafy.hallhole.member.service;

import com.ssafy.hallhole.member.domain.Member;

import java.util.List;


public interface MemberService {

    void join(Member member);

    void duplicateMember(String email);

    void findPW(String email);

    void delMem(String tag);

    void changePW(String email, String password);

    Member changeInfo(String tag, String profile,
                    String name, String gender, String age);

    Member getInfo(String email);
    Member login(String email, String password);


    List<Member> findAllMember();

    Member findKakaoMember(String sid);

}
