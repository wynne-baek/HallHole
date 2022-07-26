package com.ssafy.hallhole.comment;

import com.ssafy.hallhole.member.Member;
import com.ssafy.hallhole.performance.Performance;
import com.ssafy.hallhole.review.Review;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @JoinColumn(name = "member_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @NotNull
    @JoinColumn(name = "review_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Review review;

    @NotNull
    @Column(columnDefinition = "MEDIUMTEXT")
    private String contents;

    @NotNull
    @Builder.Default
    private boolean isDelete = false;

    @CreationTimestamp
    private LocalDateTime writingTime;

    @UpdateTimestamp
    private LocalDateTime updateTime;

}
