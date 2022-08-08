package com.ssafy.hallhole.member.controller;

import com.ssafy.hallhole.member.domain.Member;
import com.ssafy.hallhole.member.dto.TokenDto;
import com.ssafy.hallhole.member.service.OAuthServiceImpl;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/oauth")
@RequiredArgsConstructor
@ApiOperation(value = "OAuthController")
public class OAuthController {

    private final OAuthServiceImpl oAuthService;

    @GetMapping("/join")
    @ApiOperation(value="카카오 회원가입")
    public ResponseEntity join(@RequestParam String code){
        try{
            String kakaoAccessToken = oAuthService.getAccessToken(code,"join").getToken();
            Member member = oAuthService.findUserInfo(code, oAuthService.getAccessToken(code,"join"));

            oAuthService.join(member);
            return new ResponseEntity(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/login")
    @ApiOperation(value="카카오 로그인")
    public ResponseEntity login(@RequestParam String code){
        try{
            TokenDto token= oAuthService.getAccessToken(code,"login");
            Member member = oAuthService.findUserInfo(code, token);

            Member loginMember = oAuthService.login(member.getKakaoSid());
            return new ResponseEntity(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }


}