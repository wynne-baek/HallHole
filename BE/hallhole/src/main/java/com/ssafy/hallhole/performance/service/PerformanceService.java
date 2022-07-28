package com.ssafy.hallhole.performance.service;

import com.ssafy.hallhole.performance.domain.DetailPerformance;
import com.ssafy.hallhole.performance.domain.Performance;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

public interface PerformanceService {
    List<Performance> getPerformances();

    Performance findOne();

    DetailPerformance getDetail(String id);

    void initData() throws Exception;

    void getPerformanceData(String type) throws Exception;

    void getDetailPerformanceData() throws ParserConfigurationException, Exception;

    void getFacilityData() throws ParserConfigurationException, Exception;


}
