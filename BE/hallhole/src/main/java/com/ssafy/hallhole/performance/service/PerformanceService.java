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

    void likePerformance(String performance_id, String member_id);

    void unLikePerformance(String performance_id, String member_id);
}
