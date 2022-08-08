package com.ssafy.hallhole.member.service;

import com.ssafy.hallhole.member.domain.Member;
import com.ssafy.hallhole.member.dto.TokenDto;

public interface OAuthService {

    TokenDto getAccessToken (String code, String status);

    Member findUserInfo(String code, TokenDto token);

    void join(Member member);

    void duplicateMember(String sid);

    Member login(String sid);

}
