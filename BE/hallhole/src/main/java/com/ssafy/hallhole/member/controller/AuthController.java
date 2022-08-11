package com.ssafy.hallhole.member.controller;

import com.ssafy.hallhole.advice.exceptions.NotFoundException;
import com.ssafy.hallhole.member.dto.LoginDTO;
import com.ssafy.hallhole.member.dto.MemberResponseDto;
import com.ssafy.hallhole.member.dto.TokenDto;
import com.ssafy.hallhole.member.dto.TokenRequestDto;
import com.ssafy.hallhole.member.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/reissue")
    public ResponseEntity<TokenDto> reissue(@RequestBody TokenRequestDto tokenRequestDto) throws NotFoundException {
        return ResponseEntity.ok(authService.reissue(tokenRequestDto));
    }
}