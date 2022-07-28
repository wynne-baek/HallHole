package com.ssafy.hallhole.performance.service;

import com.ssafy.hallhole.performance.domain.DetailPerformance;
import com.ssafy.hallhole.performance.repository.PerformanceRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class PerformanceServiceImplTest {

    @Autowired
    PerformanceService performanceService;

    @Autowired
    PerformanceRepository performanceRepository;

    @Test
    @Rollback(false)
    void initData() throws Exception {
        performanceService.initData();
    }

    @Test
    @Rollback(false)
    void singleTest() throws Exception {
        performanceService.getDetailPerformanceData();
    }

    @Test
    void findTest() {
        DetailPerformance detailPerformance = performanceService.getDetail("PF187649");
        System.out.println(detailPerformance.getPerformance());
        System.out.println(detailPerformance.getImages());
    }
}