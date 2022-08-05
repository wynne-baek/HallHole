package com.ssafy.hallhole.member.service;

import com.ssafy.hallhole.member.domain.Member;
import com.ssafy.hallhole.member.dto.CharacterDTO;
import com.ssafy.hallhole.member.dto.MemberJoinDTO;
import com.ssafy.hallhole.member.dto.MyProfileDTO;

import java.util.List;


public interface MemberService {

    String join(MemberJoinDTO m,String sessionId);

    void duplicateMember(String email);

    void findPW(String email);

    void delMem(Long id);

    void changePW(String email, String password);

    Member changeInfo(MyProfileDTO myDto);

    Member getInfo(Long id);

    String login(String email, String password, String sessionId);

    CharacterDTO getCharacter(Long id);

    List<Member> findAllMember();

    Member findKakaoMember(String sid);

}
