package com.ssafy.hallhole.review.service;

import com.ssafy.hallhole.review.domain.Review;
import com.ssafy.hallhole.review.dto.SummaryReviewDTO;

import java.util.List;

public interface ReviewService {

    void writeReview(Review review); // review form

    void updateReview(Review review); // review detail

    void deleteReview(Long rId); // review detail

//    List<SummaryReviewDTO> getSummeryReviewInfo(String tag);

    Review getDetailReviewInfo(Long rId); //review detail
}
