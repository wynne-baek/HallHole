package com.ssafy.hallhole.review.service;

import com.ssafy.hallhole.review.domain.Review;
import com.ssafy.hallhole.review.dto.ReviewInputDTO;
import com.ssafy.hallhole.review.dto.SummaryReviewDTO;

import java.util.List;

public interface ReviewService {

    void writeReview(ReviewInputDTO reviewDto); // review form

    void updateReview(Long rId, ReviewInputDTO reviewDto); // review detail

    void deleteReview(Long rId); // review detail

    List<SummaryReviewDTO> getSummeryReviewInfo(Long mId);

    Review getDetailReviewInfo(Long rId); //review detail
}
