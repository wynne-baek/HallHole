package com.ssafy.hallhole.performance.controller;

import com.ssafy.hallhole.performance.domain.Facility;
import com.ssafy.hallhole.performance.service.PerformanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/facility")
public class FacilityController {

    private final PerformanceService performanceService;

    @GetMapping
    public List<Facility> getFacilitiesPaging(@RequestParam("start") int start,@RequestParam("size") int size){
        return performanceService.getFacilities(start,size);
    }

    @GetMapping("/{id}")
    public Facility getFacilityDetail(@PathVariable(name = "id") String id){
        return performanceService.getOneFacility(id);
    }
}
