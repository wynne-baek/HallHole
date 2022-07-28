package com.ssafy.hallhole.performance.repository;

import com.ssafy.hallhole.performance.domain.DetailPerformance;
import com.ssafy.hallhole.performance.domain.Facility;
import com.ssafy.hallhole.performance.domain.Performance;

import java.util.List;

public interface PerformanceRepository {

    void save(Performance performance);

    void saveDetail(DetailPerformance detailPerformance);

    void saveFacility(Facility facility);

    List<Performance> findAllPerformance(); //페이징 해야함

    Performance findOnePerformanceById(String id);

    DetailPerformance findOneDetailPerformance(String id);

    List<String> findAllFacility();

    Facility findOneFacility(String id);
}
