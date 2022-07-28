package com.ssafy.hallhole.performance.controller;

import com.ssafy.hallhole.performance.domain.Performance;
import com.ssafy.hallhole.performance.service.PerformanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/performance")
public class PerformanceController {

    private final PerformanceService performanceService;

    @GetMapping
    public List<Performance> getPerformances(){
        return null;
    }

}
