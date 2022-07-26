package com.ssafy.hallhole.report;

import com.ssafy.hallhole.member.Member;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member respondentMember;

    @NotNull
    private String contents;

    @NotNull
    private String type;

    @NotNull
    @Builder.Default
    @Column(length = 20)
    @Enumerated(EnumType.STRING)
    private ReportStatus status = ReportStatus.REPORTED;

    @CreationTimestamp
    private LocalDateTime dateTime;


}
