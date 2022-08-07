package com.ssafy.hallhole.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDeleteInputDTO {

    private Long memberId;

    private Long commentId;

}
