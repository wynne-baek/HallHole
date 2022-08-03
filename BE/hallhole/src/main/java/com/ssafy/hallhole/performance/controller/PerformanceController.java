package com.ssafy.hallhole.performance.controller;

import com.ssafy.hallhole.performance.domain.DetailPerformance;
import com.ssafy.hallhole.performance.domain.Performance;
import com.ssafy.hallhole.performance.dto.PerformanceSearchResult;
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
    public PerformanceSearchResult searchPerformanceByName(@RequestParam("start") int start, @RequestParam("size") int size, @PathVariable("name") String name) {
        PerformanceSearchResult performanceSearchResult = PerformanceSearchResult.builder()
                .performances(performanceService.findPerformancesByName(start,size,name))
                .PerformancesCnt(performanceService.getPerformancesCntByName(name))
                .build();
        return performanceSearchResult;
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
