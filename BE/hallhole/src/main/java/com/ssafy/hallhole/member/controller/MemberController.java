package com.ssafy.hallhole.member.controller;

import com.ssafy.hallhole.member.Member;
import com.ssafy.hallhole.member.service.MemberServiceImpl;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
@ApiOperation(value = "memberController")
public class MemberController {

    private final MemberServiceImpl memberService;

    // >>>>>>>>>>>>>>>>>  login & signup  >>>>>>>>>>>>>>>>>>>>>>>>>

    @PostMapping("/join-hh")
    @ApiOperation(value="[완료] 홀홀 회원가입")
    public ResponseEntity joinHH(@RequestBody String email, String name, String pw){
        try{
            memberService.join(new Member(email,name,pw));
            return new ResponseEntity(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    // 카카오 로그인

    @PostMapping("/login-hh")
    @ApiOperation(value="[완료] 홀홀 로그인")
    public ResponseEntity<Member> login(@RequestBody String email, String password){
        try{
            Member member = memberService.loginhh(email,password);
            return new ResponseEntity(member, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/pwmail")
    @ApiOperation(value="[완료] 비밀번호 변경 링크 메일 전송")
    public ResponseEntity findPW(@RequestBody String email){
        try{
            memberService.findPW(email);
            return new ResponseEntity(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/new-pw")
    @ApiOperation(value = "[완료] 비밀번호 링크 접속 후 변경")
    public ResponseEntity changePW(@RequestBody String email, String password){
        try{
            memberService.changePW(email,password);
            return new ResponseEntity(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/out/{memberId}")
    @ApiOperation(value = "[완료] 회원 탈퇴")
    public ResponseEntity delMember(@RequestBody @PathVariable("memberId") Long memberId){
        try{
            memberService.delMem(memberId);
            return new ResponseEntity(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/my-profile/{tag}")
    @ApiOperation(value = "[완료] 프로필 변경 (Edit Profile)")
    public ResponseEntity<Member> changeInfo(
            @RequestBody @PathVariable("tag") String tag, String profile, String name, String gender, String age){
        try{
            Member member = memberService.changeInfo(tag, profile, name, gender, age);
            return new ResponseEntity<Member>(member,HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/get-info/{tag}")
    @ApiOperation(value = "[완료] 유저 데이터 조회")
    public ResponseEntity<Member> getInfo(@RequestBody @PathVariable("tag") String tag){
        try{
            Member member = memberService.getInfo(tag);
            return new ResponseEntity<Member>(member,HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    // 팔로워 정보 가져오기: 유저 별 캐릭터 포함 (user profile)

    // 팔로잉 정보 가져오기: 유저 별 캐릭터 포함 (user profile)

    // 캐릭터 꾸미기(edit profile)

    // 유저 프로필 정보 가져오기:mem,plike(관련된 모든 것),following, comment, review, rreaction (user profile)

}
