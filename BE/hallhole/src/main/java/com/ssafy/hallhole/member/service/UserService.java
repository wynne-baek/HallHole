package com.ssafy.hallhole.member.service;

import com.ssafy.hallhole.advice.exceptions.NotFoundException;
import com.ssafy.hallhole.member.domain.Member;
import com.ssafy.hallhole.member.dto.MemberResponseDto;
import com.ssafy.hallhole.member.repository.MemberRepository;
import com.ssafy.hallhole.member.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public MemberResponseDto getMemberInfo(String email) throws NotFoundException {

        Member member = memberRepository.findByEmail(email);
        if(member==null || member.isOut()){
            throw new NotFoundException("유효한 회원이 아닙니다.");
        }

        MemberResponseDto responseDto = new MemberResponseDto(email);
        return responseDto;
    }

    // 현재 SecurityContext 에 있는 유저 정보 가져오기
    @Transactional(readOnly = true)
    public MemberResponseDto getMyInfo() throws NotFoundException {

        Member member = memberRepository.findById(SecurityUtil.getCurrentMemberId()).get();
        if(member==null || member.isOut()){
            throw new NotFoundException("로그인 유저 정보가 없습니다.");
        }

        MemberResponseDto responseDto = new MemberResponseDto(member.getEmail());
        return responseDto;
    }

}
