package com.ssafy.hallhole.performance.service;

import com.ssafy.hallhole.performance.domain.DetailPerformance;
import com.ssafy.hallhole.performance.domain.Facility;
import com.ssafy.hallhole.performance.domain.Performance;

import java.util.List;

public interface PerformanceService {
    List<Performance> getPerformances(int start, int size);

    Performance findOnePerformance(String id);

    DetailPerformance getDetail(String id);

    List<Facility> getFacilities(int start, int size);

    Facility getOneFacility(String id);

    void scheduledOpenAndCloseChat();

    List<Performance> findPerformancesByName(int start, int size, String name);

    List<Facility> findFacilitiesByPerformanceName(int start, int size, String name);

    Long getPerformancesCntByName(String name);

    Long findFacilitiesCntByPerformanceName(String name);
}
