package com.ssafy.hallhole.review.controller;

import com.ssafy.hallhole.advice.exceptions.NotFoundException;
import com.ssafy.hallhole.review.domain.ReviewReaction;
import com.ssafy.hallhole.review.dto.ReviewReactionAddInputDTO;
import com.ssafy.hallhole.review.dto.ReviewReactionListInputDTO;
import com.ssafy.hallhole.review.dto.ReviewReactionSubInputDTO;
import com.ssafy.hallhole.review.service.ReviewReactionServiceImpl;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/review-reaction")
@RequiredArgsConstructor
@ApiOperation(value = "reviewReactionController")
public class ReviewReactionController {

    private final ReviewReactionServiceImpl reviewReactionService;

    // 생성
    @PostMapping("/add")
    @ApiOperation(value="후기에 리액션 추가")
    public void addReaction(@RequestBody ReviewReactionAddInputDTO inputDto) throws NotFoundException {
        reviewReactionService.addReaction(inputDto);
    }

    @PostMapping("/reaction-type/add")
    @ApiOperation(value="리액션 타입 생성")
    public void addReactionType(@RequestBody String rTypeName) throws NotFoundException {
        reviewReactionService.makeReactionType(rTypeName);
    }

//    // 취소
//    @PostMapping("/sub")
//    @ApiOperation(value="후기 리액션 취소")
//    public void subReaction(@RequestBody ReviewReactionSubInputDTO inputDto) throws NotFoundException {
//        reviewReactionService.cancelReaction(inputDto);
//    }
//
//
//    // 유저별 리액션 검색
//    @PostMapping("/list")
//    @ApiOperation(value="멤버별 후기 리액션 리스트")
//    public List<ReviewReaction> getReactionList(@RequestBody ReviewReactionListInputDTO inputDto) throws NotFoundException {
//        return reviewReactionService.getMemberReactionList(inputDto.getStart(), inputDto.getSize(), inputDto.getMemberTag());
//    }


}
