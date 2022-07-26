package com.ssafy.hallhole.member;

import com.ssafy.hallhole.follow.Following;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String provider;

    private String kakaoSid;

    private String token;

    @NotNull
    @Column(length = 20)
    private String name;

    @NotNull
    private String email;

    @NotNull
    @Column(length = 25)
    private String password;

    @Column(length = 2)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(length = 10)
    private String age;

    @NotNull
    @Builder.Default
    private boolean isAdmin = false;

    @NotNull
    private int point;

    @NotNull
    @CreationTimestamp
    private LocalDateTime joinDate;

    @Builder.Default
    @NotNull
    private boolean isOut = false;

    @Column(length = 10, nullable = false)
    private String idTag;

    @NotNull
    @Builder.Default
    private boolean isBan = false;

    @NotNull
    @Builder.Default
    private int followingCnt = 0;

    @NotNull
    @Builder.Default
    @ColumnDefault("0")
    private int followerCnt = 0;

    @Column(length = 52)
    private String profile;

    @OneToMany(mappedBy = "followedMember")
    private List<Following> followList = new ArrayList<>();
}
