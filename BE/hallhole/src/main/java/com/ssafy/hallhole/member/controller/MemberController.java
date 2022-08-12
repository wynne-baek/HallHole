package com.ssafy.hallhole.member.controller;

import com.ssafy.hallhole.advice.exceptions.BadRequestException;
import com.ssafy.hallhole.advice.exceptions.NotFoundException;
import com.ssafy.hallhole.member.domain.Member;
import com.ssafy.hallhole.member.dto.*;
import com.ssafy.hallhole.member.service.MemberServiceImpl;
import com.ssafy.hallhole.member.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Map;
import java.util.regex.Pattern;

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
    public ResponseEntity<TokenDto> login(@RequestBody LoginDTO memberRequestDto) {
        return ResponseEntity.ok(memberService.login(memberRequestDto));
    }

//    @PostMapping("/reissue")
//    public ResponseEntity<TokenDto> reissue(@RequestBody TokenRequestDto tokenRequestDto) throws NotFoundException {
//        return ResponseEntity.ok(memberService.reissue(tokenRequestDto));
//    }

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

    @PutMapping("/out")
    @ApiOperation(value = "회원 탈퇴 >> follow 관련 상의 필요. 탈퇴 잠시 막아뒀습니다. 다른 것들 다 수정 후 올릴게요")
    public void delMember(@RequestHeader Map<String, Object> requestHeader, HttpSession session) throws NotFoundException {
//        String token = (String) requestHeader.get("token");
//        memberService.delMem(token, session.getId());
    }



    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<MemberResponseDto> getMyMemberInfo() throws NotFoundException {
        System.out.println("getMyMemberInfo()");
        return ResponseEntity.ok(userService.getMyInfo());
    }

    @GetMapping("/{email}")
    public ResponseEntity<MemberResponseDto> getMemberInfo(@PathVariable String email) throws NotFoundException {
        System.out.println("getMemberInfo()");
        return ResponseEntity.ok(userService.getMemberInfo(email));
    }













    @PostMapping("/chk-email")
    @ApiOperation(value="이메일 형식 체크", notes = "이메일 형식이 맞는지 boolean 형식으로 return")
    public boolean emailCheck(@RequestBody String email){
        return Pattern.matches("\\w+@\\w+\\.\\w+(\\.\\w+)?", email);
    }

//    @PostMapping("/chk-duplicate")
//    @ApiOperation(value="이메일 중복 체크", notes = "이메일 중복인지 boolean 형식으로 return >> null이거나, 중복이면 return false")
//    public boolean emailDuplicateCheck(@RequestBody String email) throws NotFoundException {
//        if(email==null) return false;
//        try{
//            memberService.duplicateMember(email);
//            return true;
//        }catch(NotFoundException e){
//            return false;
//        }
//    }

    @PostMapping("/chk-pw")
    @ApiOperation(value="비밀번호 체크",
            notes =  "비밀번호 형식: 8-20자, 숫자/특수문자($`~!@$!%*#^?&()_=+)/영문자 필수 >> boolean 형식으로 return")
    public boolean pwCheck(@RequestBody String pw){
        return Pattern.matches("^(?=.*[a-zA-z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\\\(\\\\)\\-_=+])(?!.*[^a-zA-z0-9$`~!@$!%*#^?&\\\\(\\\\)\\-_=+]).{8,20}$" , pw);
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
    public ResponseEntity findPW(@RequestBody String email) throws NotFoundException {
        try{
            memberService.findPW(email);
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

    @PutMapping("")
    @ApiOperation(value = "프로필 변경")
    public MemberOutputDTO changeInfo(@RequestBody MyProfileDTO myDto) throws NotFoundException {
        return memberService.changeInfo(myDto);
    }

    @GetMapping("/info/{tag}")
    @ApiOperation(value = "유저 데이터 조회", notes = "'/member/info/JVWUZ9HZ9W' 형식으로 사용. tag = 멤버태그")
    public MemberOutputDTO getInfo(@PathVariable("tag") String tag) throws NotFoundException {
        return memberService.getInfo(tag);
    }

    @GetMapping("/deco/{tag}")
    @ApiOperation(value = "현재 멤버 캐릭터 배경, 캐릭터, 악세사리 값 가져오기",
            notes = "'/member/deco/JVWUZ9HZ9W' 형식으로 사용. tag = 멤버태그")
    public CharacterDTO getCharacter(@PathVariable("tag") String tag) throws NotFoundException{
        return memberService.getCharacter(tag);
    }

    @PostMapping("/ban/{tag}")
    @ApiOperation(value = "유저 밴 시키기", notes = "'/member/ban/JVWUZ9HZ9W' 형식으로 사용. tag = 멤버태그")
    public void makeBan(@PathVariable("tag") String tag) throws NotFoundException{
        memberService.makeBan(tag);
    }

    @PostMapping("/cancel-ban/{tag}")
    @ApiOperation(value = "유저 밴 풀기", notes = "'/member/cancel-ban/JVWUZ9HZ9W' 형식으로 사용. tag = 멤버태그")
    public void cancelBan(@PathVariable("tag") String tag) throws NotFoundException{
        memberService.cancelBan(tag);
    }



}
