package com.ssafy.hallhole.member.dto;

import com.ssafy.hallhole.member.domain.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MyProfileDTO {

    private Long id;

    @Setter
    private String name;

    private String email;

    private String idTag;

    @Setter
    private Gender gender;

    @Setter
    private LocalDate birth;

    @Setter
    private int nowBg;

    @Setter
    private int nowChar;

    @Setter
    private int nowAcc;

}
