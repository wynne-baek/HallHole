package com.ssafy.hallhole.review.domain;

import com.ssafy.hallhole.member.domain.Member;
import com.ssafy.hallhole.performance.Performance;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.*;

import javax.persistence.Entity;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
public class Review {

    @Id
    @Column(columnDefinition = "INT UNSIGNED")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @JoinColumn(name = "member_id")
    @ManyToOne(fetch = FetchType.LAZY)
//    @Column(columnDefinition = "INT UNSIGNED")
    private Member member;

    @Setter
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "performance_id")
    private Performance performance;

    @Setter
    @NotNull
    @Column(length = 65)
    private String title;

    @Setter
    @NotNull
    private LocalDateTime performanceDatetime;

    @Setter
    @NotNull
    private String contents;

    @NotNull
    @CreationTimestamp
    private LocalDateTime writingTime;

    @UpdateTimestamp
    private LocalDateTime updateTime;

    @Setter
    private double starEval;

    @Setter
    @Builder.Default
    @ColumnDefault("false")
    private boolean isDelete = false;
}
