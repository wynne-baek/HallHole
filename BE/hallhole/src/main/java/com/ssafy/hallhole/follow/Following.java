package com.ssafy.hallhole.follow;

import com.ssafy.hallhole.member.domain.Member;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
@Table(uniqueConstraints = {@UniqueConstraint(name = "follow", columnNames = {"following_member_id", "followed_member_id"})})
public class Following {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Member followingMember;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Member followedMember;

    @CreationTimestamp
    private LocalDateTime dateTime;

    public void follow(Member following, Member followed) {
        following.addFollowingCnt();
        followed.addFollowerCnt();

        following.addFollowList(this);
    }

    public void unfollow(Member following, Member followed) {
        following.subFollowingCnt();
        followed.subFollowerCnt();

        following.removeFollowList(this);
    }
}
