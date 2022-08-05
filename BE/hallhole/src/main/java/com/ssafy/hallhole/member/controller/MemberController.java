package com.ssafy.hallhole.member.controller;

import com.ssafy.hallhole.member.domain.Member;
import com.ssafy.hallhole.member.dto.*;
import com.ssafy.hallhole.member.service.MemberServiceImpl;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Arrays;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
@ApiOperation(value = "memberController")
public class MemberController {

    private final MemberServiceImpl memberService;

    @PostMapping("/join")
    @ApiOperation(value="[완료] 홀홀 회원가입")
    public ResponseEntity join(@RequestBody MemberJoinDTO member, HttpSession session){
        try{
            String token = memberService.join(member, session.getId());
            TokenDto tokenDto = new TokenDto(token);
            return new ResponseEntity(tokenDto,HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    @ApiOperation(value="[완료] 홀홀 로그인")
    public ResponseEntity<TokenDto> login(@RequestBody LoginDTO member,HttpSession session){
        try{
            String token = memberService.login(member.getEmail(), member.getPw(), session.getId());
            TokenDto tokenDto = new TokenDto(token);
            return new ResponseEntity(tokenDto,HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/pwmail")
    @ApiOperation(value="[완료] 비밀번호 변경 링크 메일 전송")
    public ResponseEntity findPW(@RequestBody EmailDTO emailDto){
        try{
            memberService.findPW(emailDto.getEmail());
            return new ResponseEntity(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/new-pw")
    @ApiOperation(value = "[완료] 비밀번호 링크 접속 후 변경")
    public ResponseEntity changePW(@RequestBody LoginDTO member){
        try{
            memberService.changePW(member.getEmail(), member.getPw());
            return new ResponseEntity(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/out")
    @ApiOperation(value = "[완료] 회원 탈퇴")
    public ResponseEntity delMember(@RequestBody IDDTO iddto){
        try{
            memberService.delMem(iddto.getId());
            return new ResponseEntity(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("")
    @ApiOperation(value = "[완료] 프로필 변경")
    public ResponseEntity<Member> changeInfo(@RequestBody MyProfileDTO myDto){
        try{
            Member member = memberService.changeInfo(myDto);
            return new ResponseEntity(member,HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("")
    @ApiOperation(value = "[완료] 유저 데이터 조회")
    public ResponseEntity<Member> getInfo(@RequestBody IDDTO iddto){
        try{
            Member member = memberService.getInfo(iddto.getId());
            return new ResponseEntity(member,HttpStatus.OK);
        }catch(Exception e){
            System.out.println("memberController");
            System.out.println(Arrays.toString(e.getStackTrace()));
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/deco")
    @ApiOperation(value = "캐릭터 꾸미기 현재")
    public ResponseEntity<CharacterDTO> getCharacter(@RequestBody IDDTO iddto){
        try{
            CharacterDTO current = memberService.getCharacter(iddto.getId());
            return new ResponseEntity(current,HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }




}
