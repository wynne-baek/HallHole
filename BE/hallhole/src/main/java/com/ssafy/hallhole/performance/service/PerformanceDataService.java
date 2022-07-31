package com.ssafy.hallhole.performance.service;

import com.ssafy.hallhole.performance.domain.Facility;

public interface PerformanceDataService {

    void scheduledData();

    void initData() throws Exception;

    void getPerformanceData(String type) throws Exception;

    void getFacilityData() throws Exception;

    Facility getFacilityDetailData(String id) throws Exception;

    void getDetailPerformanceData() throws Exception;
}
