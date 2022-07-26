package com.ssafy.hallhole.point;

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
public class PointLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @JoinColumn(name = "member_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @NotNull
    @Column(length = 10)
    @Enumerated(EnumType.STRING)
    private LogType type;

    @CreationTimestamp
    private LocalDateTime timestamp;

    @NotNull
    private int point;
}
