package com.ssafy.hallhole.review.controller;

import com.ssafy.hallhole.review.Review;
import com.ssafy.hallhole.review.dto.SummeryReviewDTO;
import com.ssafy.hallhole.review.service.ReviewServiceImpl;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
@ApiOperation(value = "reviewController")
public class ReviewController {

    private final ReviewServiceImpl reviewService;

    @PostMapping("/write")
    @ApiOperation(value="[완료] 리뷰 작성")
    public ResponseEntity writeReview(@RequestBody Review review){
        try{
            reviewService.writeReview(review);
            return new ResponseEntity(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/{reviewId}")
    @ApiOperation(value="[완료] 리뷰 수정")
    public ResponseEntity updateReview(@RequestBody @PathVariable("reviewId") Long reviewId, Review review){
        try{
            reviewService.updateReview(review);
            return new ResponseEntity(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{reviewId}")
    @ApiOperation(value="[완료] 리뷰 삭제")
    public ResponseEntity<Review> deleteReview(@RequestBody @PathVariable("reviewId") Long reviewId){
        try{
            reviewService.deleteReview(reviewId);
            return new ResponseEntity(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{reviewId}")
    @ApiOperation(value="[완료] 후기 상세 페이지(Review Detail)")
    public ResponseEntity<Review> getDetailReview(@RequestBody @PathVariable("reviewId") Long reviewId){
        try{
            Review review = reviewService.getDetailReviewInfo(reviewId);
            return new ResponseEntity<Review>(review,HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}


//    @GetMapping("/{pid}")
//    @ApiOperation(value="리뷰 요약 리스트 받아오기")
//    public ResponseEntity<SummeryReviewDTO> getReviewList(@RequestBody @PathVariable("pid") String pid){
//        try{
//            List<SummeryReviewDTO> srList = reviewService.getSummeryReviewInfo(pid);
//            return new ResponseEntity(HttpStatus.OK);
//        }catch(Exception e){
//            return new ResponseEntity(HttpStatus.BAD_REQUEST);
//        }
//    }