package com.ssafy.hallhole.member.service;

import com.ssafy.hallhole.advice.exceptions.NotFoundException;
import com.ssafy.hallhole.member.domain.Member;
import com.ssafy.hallhole.member.dto.TokenDto;
import com.ssafy.hallhole.member.dto.TokenRequestDto;
import com.ssafy.hallhole.member.jwt.TokenProvider;
import com.ssafy.hallhole.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final TokenProvider tokenProvider;

    private final MemberRepository memberRepository;

    @Transactional
    public TokenDto reissue(TokenRequestDto tokenRequestDto) throws NotFoundException {
        // 1. Refresh Token 검증
        if (!tokenProvider.validateToken(tokenRequestDto.getRefreshToken())) {
            throw new RuntimeException("Refresh Token 이 유효하지 않습니다.");
        }

        // 2. Access Token 에서 Member ID 가져오기
        Authentication authentication = tokenProvider.getAuthentication(tokenRequestDto.getAccessToken());

        // 3. 저장소에서 Member ID 를 기반으로 Refresh Token 값 가져옴
//        RefreshToken refreshToken = refreshTokenRepository.findByKey(authentication.getName())
//                .orElseThrow(() -> new RuntimeException("로그아웃 된 사용자입니다."));
        Member m = memberRepository.findByIdTag(authentication.getName());
        if(m==null || m.isOut()){
            throw new NotFoundException("유효한 회원이 아닙니다.");
        }

        String refreshToken = m.getRefreshToken();
        System.out.println("authentication = " + authentication.getName());
        System.out.println(m.getEmail());
        System.out.println("refreshToken = " + refreshToken);


        // 4. Refresh Token 일치하는지 검사
        if (!refreshToken.equals(tokenRequestDto.getRefreshToken())) {
            throw new RuntimeException("토큰의 유저 정보가 일치하지 않습니다.");
        }

        // 5. 새로운 토큰 생성
        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);

        // 6. 저장소 정보 업데이트
//        RefreshToken newRefreshToken = refreshToken.updateValue(tokenDto.getRefreshToken());
//        refreshTokenRepository.save(newRefreshToken);
        m.setRefreshToken(tokenDto.getRefreshToken());
        memberRepository.save(m);
        // 토큰 발급
        return tokenDto;
    }
}
