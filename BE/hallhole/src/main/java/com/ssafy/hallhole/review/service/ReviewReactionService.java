package com.ssafy.hallhole.review.service;

import com.ssafy.hallhole.advice.exceptions.NotFoundException;
import com.ssafy.hallhole.review.domain.ReviewReaction;
import com.ssafy.hallhole.review.dto.ReviewReactionAddInputDTO;
import com.ssafy.hallhole.review.dto.ReviewReactionSubInputDTO;

import java.util.List;

public interface ReviewReactionService {
    void addReaction(ReviewReactionAddInputDTO inputDto) throws NotFoundException;

    void cancelReaction(ReviewReactionSubInputDTO inputDTO) throws NotFoundException;

    List<ReviewReaction> getMemberReactionList(int start, int size, String memberTag) throws NotFoundException;

    void makeReactionType(String reaction) throws NotFoundException;
}
