package com.ssafy.hallhole.member.service;

import com.ssafy.hallhole.advice.exceptions.BadRequestException;
import com.ssafy.hallhole.advice.exceptions.NotFoundException;
import com.ssafy.hallhole.member.domain.Member;
import com.ssafy.hallhole.member.dto.*;

import java.util.List;


public interface MemberService {

    void join(MemberJoinDTO m,String sessionId) throws NotFoundException;

    void logout(String token, String sessionId) throws NotFoundException;

    void duplicateMember(String email) throws NotFoundException;

    void findPW(String email) throws NotFoundException;

    void delMem(String token,String sessionId) throws NotFoundException;

    void changePW(String email, String password) throws NotFoundException;

    MemberOutputDTO changeInfo(MyProfileDTO myDto) throws NotFoundException;

    MemberOutputDTO getInfo(String tag) throws NotFoundException;

//    String login(String email, String password, String sessionId) throws NotFoundException;

    TokenDto login(LoginDTO memberRequestDto);

    CharacterDTO getCharacter(String tag) throws NotFoundException;

    List<Member> findAllMember() throws NotFoundException;

    Member findKakaoMember(String sid) throws NotFoundException;

    MemberOutputDTO findInfo(String token) throws NotFoundException;

    void makeBan(String tag) throws NotFoundException;

    void cancelBan(String tag) throws NotFoundException;
}
