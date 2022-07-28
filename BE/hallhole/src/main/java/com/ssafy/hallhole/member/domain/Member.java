package com.ssafy.hallhole.member.domain;

import com.ssafy.hallhole.follow.Following;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
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

    @Setter
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
    @ColumnDefault("false")
    private boolean isAdmin = false;

    @NotNull
    @Builder.Default
    @ColumnDefault("0")
    private int point = 0;

    @NotNull
    @CreationTimestamp
    private LocalDateTime joinDate;

    @Setter
    @NotNull
    @Builder.Default
    @ColumnDefault("false")
    private boolean isOut = false;

    @Column(length = 10, nullable = false)
    private String idTag;

    @Setter
    @NotNull
    @Builder.Default
    @ColumnDefault("false")
    private boolean isBan = false;

    @NotNull
    @Builder.Default
    @ColumnDefault("0")
    private int followingCnt = 0;

    @NotNull
    @Builder.Default
    @ColumnDefault("0")
    private int followerCnt = 0;

    @Setter
    @Column(length = 52)
    private String profile;

    @OneToMany(mappedBy = "followedMember")
    private List<Following> followList = new ArrayList<>();

    public void addFollowingCnt() {
        this.followingCnt++;
    }

    public void subFollowingCnt() {
        this.followingCnt--;
    }

    public void addFollowerCnt() {
        this.followerCnt++;
    }

    public void subFollowerCnt() {
        this.followerCnt--;
    }

    public void addPoint(int delta) {
        this.point += delta;
    }

    public void subPoint(int delta) {
        int remainPoint = this.point - delta;
        if (remainPoint < 0) {
            throw new IllegalStateException("포인트가 부족합니다.");
        }
        this.point = remainPoint;
    }

    public void addFollowList(Following following) {
        this.followList.add(following);
    }

    public void removeFollowList(Following following) {
        this.followList.remove(following);
    }
}
