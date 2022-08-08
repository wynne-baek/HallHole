package com.ssafy.hallhole.member.controller;

import com.ssafy.hallhole.advice.exceptions.BadRequestException;
import com.ssafy.hallhole.advice.exceptions.NotFoundException;
import com.ssafy.hallhole.member.domain.Member;
import com.ssafy.hallhole.member.dto.*;
import com.ssafy.hallhole.member.service.MemberServiceImpl;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Map;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
@ApiOperation(value = "memberController")
public class MemberController {

    private final MemberServiceImpl memberService;

    @PostMapping("/join")
    @ApiOperation(value="홀홀 회원가입", notes = "토큰 만료 수정 예정, 가입 축하 메일 막아뒀습니다")
    public void join(@RequestBody MemberJoinDTO member, HttpSession session) throws NotFoundException {
        memberService.join(member, session.getId());
    }

    @PostMapping("/login")
    @ApiOperation(value="홀홀 로그인", notes = "세션 정보와 아이디, 비밀번호 필요")
    public TokenDto login(@RequestBody LoginDTO member,HttpSession session) throws NotFoundException {
        String token = memberService.login(member.getEmail(), member.getPw(), session.getId());
        return new TokenDto(token);
    }

    @GetMapping("/logout")
    @ApiOperation(value="홀홀 로그아웃", notes = "세션 정보와 헤더 내 토큰 값 필요. 토큰 문제 수정 예정")
    public void logout(@RequestHeader Map<String, Object> requestHeader,HttpSession session) throws NotFoundException {
        try{
            String token = (String) requestHeader.get("token");
            memberService.logout(token, session.getId());
        }catch(Exception e){
            throw new NotFoundException("로그아웃 실패");
        }
    }

    @GetMapping("/my-info")
    @ApiOperation(value="내 정보 토큰으로 받아오기")
    public MemberOutputDTO getMyInfo(@RequestHeader Map<String, Object> requestHeader) throws NotFoundException {
        try{
            String token = (String) requestHeader.get("token");
            return memberService.findInfo(token);
        }catch(Exception e){
            throw new NotFoundException("토큰이 넘어오지 않았거나, 유효한 토큰이 아닙니다.");
        }
    }

    @PostMapping("/pwmail")
    @ApiOperation(value="비밀번호 변경 링크 메일 전송 >> 전송 막아뒀습니다")
    public ResponseEntity findPW(@RequestBody EmailDTO emailDto) throws NotFoundException {
        try{
            memberService.findPW(emailDto.getEmail());
            return new ResponseEntity(HttpStatus.OK);
        }catch(NotFoundException e){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/new-pw")
    @ApiOperation(value = "비밀번호 링크 접속 후 변경")
    public ResponseEntity changePW(@RequestBody LoginDTO member)  throws NotFoundException {
        try{
            memberService.changePW(member.getEmail(), member.getPw());
            return new ResponseEntity(HttpStatus.OK);
        }catch(NotFoundException e){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/out")
    @ApiOperation(value = "회원 탈퇴 >> follow 관련 상의 필요. 탈퇴 잠시 막아뒀습니다. 다른 것들 다 수정 후 올릴게요")
    public void delMember(@RequestHeader Map<String, Object> requestHeader, HttpSession session) throws NotFoundException {
//        String token = (String) requestHeader.get("token");
//        memberService.delMem(token, session.getId());
    }

    @PutMapping("")
    @ApiOperation(value = "프로필 변경")
    public MemberOutputDTO changeInfo(@RequestBody MyProfileDTO myDto) throws NotFoundException {
        return memberService.changeInfo(myDto);
    }

    @PostMapping("/info")
    @ApiOperation(value = "유저 데이터 조회")
    public MemberOutputDTO getInfo(@RequestBody TagDTO tagDTO) throws NotFoundException {
        return memberService.getInfo(tagDTO.getIdTag());
    }

    @PostMapping("/deco")
    @ApiOperation(value = "캐릭터 꾸미기 현재")
    public CharacterDTO getCharacter(@RequestBody TagDTO tagDTO) throws NotFoundException{
        return memberService.getCharacter(tagDTO.getIdTag());
    }

    @PostMapping("/ban")
    @ApiOperation(value = "유저 밴 시키기")
    public void makeBan(@RequestBody TagDTO tagDTO) throws NotFoundException{
        memberService.makeBan(tagDTO.getIdTag());
    }

    @PostMapping("/cancel-ban")
    @ApiOperation(value = "유저 밴 풀기")
    public void cancelBan(@RequestBody TagDTO tagDTO) throws NotFoundException{
        memberService.cancelBan(tagDTO.getIdTag());
    }

}
