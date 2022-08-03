package com.ssafy.hallhole.performance.controller;

import com.ssafy.hallhole.performance.domain.DetailPerformance;
import com.ssafy.hallhole.performance.domain.Performance;
import com.ssafy.hallhole.performance.service.PerformanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/performance")
public class PerformanceController {
    private final PerformanceService performanceService;

    @GetMapping
    public List<Performance> getPerformances(@RequestParam("start") int start, @RequestParam("size") int size) {
        return performanceService.getPerformances(start, size);
    }

    @GetMapping("/{id}")
    public DetailPerformance getPerformanceDetail(@PathVariable("id") String id) {
        return performanceService.getDetail(id);
    }

    @GetMapping("/search/{name}")
    public List<Performance> searchPerformanceByName(@RequestParam("start") int start, @RequestParam("size") int size, @PathVariable("name") String name) {
        return performanceService.findPerformancesByName(start,size,name);
    }

    @PostMapping("/like")
    public void likePerformance(@RequestBody PerformanceLikeVo performancelikeVo) {
        performanceService.likePerformance(performancelikeVo.performance_id, performancelikeVo.member_id);
    }

    @PostMapping("/unlike")
    public void unLikePerformance(@RequestBody PerformanceLikeVo performancelikeVo) {
        performanceService.unLikePerformance(performancelikeVo.performance_id, performancelikeVo.member_id);
    }

    @GetMapping("/test")
    public void createTest(){
        performanceService.scheduledOpenAndCloseChat();
    }

    class PerformanceLikeVo {
        String performance_id;
        String member_id;
    }

}
