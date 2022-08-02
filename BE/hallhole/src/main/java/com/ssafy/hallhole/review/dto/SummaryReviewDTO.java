package com.ssafy.hallhole.review.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SummaryReviewDTO {

    private Long id;
    private Long member_id;
    private String title;
    private LocalDateTime writing_time;
    private Double star_eval;

    @Setter
    private String name;

    @Setter
    private int memberBg;

    @Setter
    private int memberChar;

    @Setter
    private int memberAcc;

    public SummaryReviewDTO(Long id, Long member_id, String title, LocalDateTime writing_time, Double star_eval){
        this.id = id;
        this.member_id=member_id;
        this.title=title;
        this.writing_time=writing_time;
        this.star_eval=star_eval;
    }

}
