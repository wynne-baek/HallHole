package com.ssafy.hallhole.member.controller;

import com.ssafy.hallhole.member.domain.Member;
import com.ssafy.hallhole.member.service.MemberServiceImpl;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberServiceImpl memberService;

    @ApiOperation(value="회원가입", response = ResponseEntity.class)
    @GetMapping("/join")
    public ResponseEntity memberJoin(@RequestBody Member member){
        try{
            memberService.join(member);
            return new ResponseEntity(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

}
