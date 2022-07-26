package com.ssafy.hallhole.report;

import com.ssafy.hallhole.member.Member;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
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
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @JoinColumn(name = "member_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @NotNull
    @JoinColumn(name = "member_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Member respondentMember;

    @NotNull
    private String contents;

    @NotNull
    private String type;

    @Setter
    @NotNull
    @Builder.Default
    @Column(length = 20)
    @Enumerated(EnumType.STRING)
    @ColumnDefault("REPORTED")
    private ReportStatus status = ReportStatus.REPORTED;

    @CreationTimestamp
    private LocalDateTime dateTime;

}
