package com.ssafy.hallhole.review.controller;

import com.ssafy.hallhole.advice.exceptions.NotFoundException;
import com.ssafy.hallhole.review.domain.Review;
import com.ssafy.hallhole.review.dto.*;
import com.ssafy.hallhole.review.service.ReviewServiceImpl;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.LifecycleState;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
@ApiOperation(value = "reviewController")
public class ReviewController {

    private final ReviewServiceImpl reviewService;

    @PostMapping("/write")
    @ApiOperation(value="리뷰 작성")
    public void writeReview(@RequestBody ReviewInputDTO reviewDto) throws NotFoundException {
        reviewService.writeReview(reviewDto);
    }

    @PostMapping("/{reviewId}")
    @ApiOperation(value="리뷰 수정",
            notes = "writerTag는 리뷰의 작성자가 아닌 현재 수정하려는 사람의 tag로 넣어주세요")
    public void updateReview(@PathVariable("reviewId") Long reviewId, @RequestBody ReviewInputDTO reviewDto) throws NotFoundException {
        reviewService.updateReview(reviewId,reviewDto);
    }

    @PutMapping("/{reviewId}")
    @ApiOperation(value="리뷰 삭제",
            notes = "writerTag는 리뷰의 작성자가 아닌 현재 삭제하려는 사람의 tag로 넣어주세요")
    public void deleteReview(@PathVariable("reviewId") Long reviewId, @RequestBody ReviewDeleteDTO inputDto) throws NotFoundException {
        reviewService.deleteReview(reviewId, inputDto);
    }

    @GetMapping("/{reviewId}")
    @ApiOperation(value="리뷰 상세 정보 가져오기")
    public ReviewOutputDTO getDetailReview(@PathVariable("reviewId") Long reviewId) throws NotFoundException {
        return reviewService.getDetailReviewInfo(reviewId);
    }

    @PostMapping("/user-review-list")
    @ApiOperation(value="유저별 후기 요약 리스트")
    public List<SummaryReviewDTO> getUserSummaryList(@RequestBody ReviewUserListDTO inputDto) throws NotFoundException  {
        return reviewService.getUserSummeryReviewInfo(inputDto.getStart(), inputDto.getSize(), inputDto.getWriterTag());
    }

    @PostMapping("/performance-review-list")
    @ApiOperation(value="공연별 후기 요약 리스트")
    public List<SummaryReviewDTO> getPerformanceSummaryList(@RequestBody ReviewPerformanceListDTO inputDto) throws NotFoundException  {
        return reviewService.getPerformanceSummeryReviewInfo(inputDto.getStart(), inputDto.getSize(), inputDto.getPerformance_id());
    }
}