package com.ssafy.hallhole.performance.controller;

import com.ssafy.hallhole.advice.exceptions.NotFoundException;
import com.ssafy.hallhole.performance.domain.Performance;
import com.ssafy.hallhole.performance.dto.PerformanceLikeInputDTO;
import com.ssafy.hallhole.performance.dto.PerformanceLikePagingInputDTO;
import com.ssafy.hallhole.performance.service.PerformanceLikeServiceImpl;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/plike")
public class PerformanceLikeController {

    private final PerformanceLikeServiceImpl pLikeService;

    @PostMapping
    @ApiOperation(value = "좋아요 생성",notes = "")
    public void makeLike(@RequestBody PerformanceLikeInputDTO inputDto) throws NotFoundException {
        pLikeService.makeLike(inputDto.getPerformanceId(), inputDto.getMemberTag());
    }

    @DeleteMapping
    @ApiOperation(value = "좋아요 취소",notes = "")
    public void cancelLike(@RequestBody PerformanceLikeInputDTO inputDto) throws NotFoundException {
        pLikeService.cancelLike(inputDto.getPerformanceId(), inputDto.getMemberTag());
    }

    @GetMapping("/{pid}")
    @ApiOperation(value = "공연별 좋아요 수",notes = "공연 별로 좋아요 한 사람 목록 필요한지 물어보고 생성하기")
    public Long findPerformanceLikeCnt(@RequestParam("pid") String pid) throws NotFoundException {
        return pLikeService.findByPerformanceCnt(pid);
    }

    @PostMapping("/list")
    @ApiOperation(value = "유저 별 좋아요 페이징",notes = "")
    public List<Performance> findPerformanceLikeByMember(@RequestBody PerformanceLikePagingInputDTO inputDto) throws NotFoundException {
        return pLikeService.findPerformanceLikePagingByMemberId(inputDto);
    }

    @GetMapping("/popular/{size}")
    @ApiOperation(value = "좋아요가 높은 공연",
            notes = "더 많이 찾아야할 필요가 있는지 물어보고 수정")
    public List<Performance> findPopularPerformance(@PathVariable("size") int size) throws NotFoundException {
        return pLikeService.findPopularPerformance(size);
    }

}
