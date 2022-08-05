package com.ssafy.hallhole.review.service;

import com.ssafy.hallhole.advice.exceptions.NotFoundException;
import com.ssafy.hallhole.member.domain.Member;
import com.ssafy.hallhole.member.repository.MemberRepository;
import com.ssafy.hallhole.performance.domain.Performance;
import com.ssafy.hallhole.performance.repository.PerformanceRepository;
import com.ssafy.hallhole.review.domain.Review;
import com.ssafy.hallhole.review.dto.ReviewInputDTO;
import com.ssafy.hallhole.review.dto.SummaryReviewDTO;
import com.ssafy.hallhole.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final PerformanceRepository performanceRepository;

    @Override
    public void writeReview(ReviewInputDTO reviewDto) throws NotFoundException { // review form

        Performance p = performanceRepository.findOnePerformanceById(reviewDto.getPerformance_id());
        Member m = memberRepository.findById(reviewDto.getWriter_id()).get();
        Review r = new Review(m,p,reviewDto.getTitle(),reviewDto.getPerformance_time(),
                reviewDto.getContents(), reviewDto.getStar());
        reviewRepository.save(r);
    }

    @Override
    public void updateReview(Long rId, ReviewInputDTO reviewDto) throws NotFoundException { // review detail
        Performance p = performanceRepository.findOnePerformanceById(reviewDto.getPerformance_id());
        Member m = memberRepository.findById(reviewDto.getWriter_id()).get();
        Review r = reviewRepository.findById(rId).get();
        Review review = new Review(rId,m,p,reviewDto.getTitle(),reviewDto.getPerformance_time(),
                reviewDto.getContents(), r.getWritingTime(), LocalDateTime.now(), reviewDto.getStar(),r.isDelete());
        reviewRepository.save(review);
    }

    @Override
    public void deleteReview(Long rId) { // review detail
        reviewRepository.deleteById(rId);
    }

    @Override
    public Review getDetailReviewInfo(Long rId) { // review detail
        Review review = reviewRepository.findById(rId).get();
        return review;
    }

    @Override
    public List<SummaryReviewDTO> getSummeryReviewInfo(Long mId) {
        Member member = memberRepository.findById(mId).get();

        if(member==null){
            throw new IllegalStateException("사용자 입력 오류");
        }

        List<Review> reviewList = reviewRepository.findAllByMemberId(member.getId());
        List<SummaryReviewDTO> summaryList = new LinkedList<>();

        for(Review r : reviewList){
            Member writer = r.getMember();
            SummaryReviewDTO s = new SummaryReviewDTO(r.getId(),writer.getId(),r.getTitle(), r.getWritingTime(),
                    r.getStarEval(),writer.getName(),writer.getNowBg(),writer.getNowChar(), writer.getNowAcc());
            if(r.getUpdateTime()!=null) s.setWriting_time(r.getUpdateTime());
            summaryList.add(s);
        }

        return summaryList;
    }
}
