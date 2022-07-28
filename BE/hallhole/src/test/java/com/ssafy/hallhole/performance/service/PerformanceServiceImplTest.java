package com.ssafy.hallhole.performance.service;

import com.ssafy.hallhole.performance.domain.Performance;
import com.ssafy.hallhole.performance.repository.PerformanceRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.time.LocalDateTime;

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
    void repoTest() throws Exception {
        performanceService.getFacilityData();
    }
}