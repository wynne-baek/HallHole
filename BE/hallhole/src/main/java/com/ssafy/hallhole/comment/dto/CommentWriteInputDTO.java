package com.ssafy.hallhole.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentWriteInputDTO {

    private Long memberId;

    private Long reviewId;

    private String contents;

}
