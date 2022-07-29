package com.ssafy.hallhole.review.service;

import com.ssafy.hallhole.member.Member;
import com.ssafy.hallhole.member.repository.MemberRepository;
import com.ssafy.hallhole.review.Review;
import com.ssafy.hallhole.review.dto.SummeryReviewDTO;
import com.ssafy.hallhole.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

    @Override
    public List<SummeryReviewDTO> getSummeryReviewInfo(String pId) { // (performance detail)
        List<SummeryReviewDTO> summeryReviewList = reviewRepository.findAllByPerformanceId(pId);
        for(int i=0;i<summeryReviewList.size();i++){
            SummeryReviewDTO sr = summeryReviewList.get(i);
            Member member = memberRepository.findById(sr.getMember_id()).get();
            summeryReviewList.get(i).setName(member.getName());
            summeryReviewList.get(i).setMemberBg(member.getNowBg());
            summeryReviewList.get(i).setMemberChar(member.getNowChar());
            summeryReviewList.get(i).setMemberAcc(member.getNowAcc());
        }

        return summeryReviewList;
    }

    @Override
    public Review getDetailReviewInfo(Long rId) { // review detail
        Review review = reviewRepository.findById(rId).get();
        return review;
    }
}
