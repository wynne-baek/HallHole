package com.ssafy.hallhole.review.service;

import com.ssafy.hallhole.member.domain.Member;
import com.ssafy.hallhole.member.repository.MemberRepository;
import com.ssafy.hallhole.review.domain.Review;
import com.ssafy.hallhole.review.dto.SummaryReviewDTO;
import com.ssafy.hallhole.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;

    @Override
    public void writeReview(Review review) { // review form
        reviewRepository.save(review);
    }

    @Override
    public void updateReview(Review review) { // review detail
        reviewRepository.save(review);
    }

    @Override
    public void deleteReview(Long rId) { // review detail
        reviewRepository.deleteById(rId);
    }

//    @Override
//    public List<SummaryReviewDTO> getSummeryReviewInfo(String tag) {
//        Member member = memberRepository.findByIdTag(tag);
//        if(member!=null){
//            throw new IllegalStateException("해당 태그에 대한 사용자가 존재하지 않습니다.");
//        }
//
//        List<Review> reviewList = reviewRepository.findAllByMemberId(member.getId());
//        List<SummaryReviewDTO> summaryList = new LinkedList<>();
//
//        for(Review r : reviewList){
//            SummaryReviewDTO s = new SummaryReviewDTO();
//        }
//
//
//        return summeryReviewList;
//    }

    @Override
    public Review getDetailReviewInfo(Long rId) { // review detail
        Review review = reviewRepository.findById(rId).get();
        return review;
    }
}
