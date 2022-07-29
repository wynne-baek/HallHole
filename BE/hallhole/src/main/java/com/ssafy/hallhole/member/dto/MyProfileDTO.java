package com.ssafy.hallhole.member.dto;

import com.ssafy.hallhole.member.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private String age;

    @Setter
    private int nowBg;

    @Setter
    private int nowChar;

    @Setter
    private int nowAcc;

}
