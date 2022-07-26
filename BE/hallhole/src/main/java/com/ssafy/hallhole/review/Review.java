package com.ssafy.hallhole.review;

import com.ssafy.hallhole.member.Member;
import com.ssafy.hallhole.performance.Performance;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT UNSIGNED")
    private int id;

    @NotNull
    @Column(columnDefinition = "INT UNSIGNED")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "performance_id")
    private Performance performance;

    @NotNull
    @Column(length = 65)
    private String title;

    @NotNull
    private LocalDateTime performanceDatetime;

    @NotNull
    private String contents;

    @NotNull
    @CreationTimestamp
    private LocalDateTime writingTime;

    private LocalDateTime updateTime;

    private double starEval;

    @Builder.Default
    private boolean isDelete = false;

}
