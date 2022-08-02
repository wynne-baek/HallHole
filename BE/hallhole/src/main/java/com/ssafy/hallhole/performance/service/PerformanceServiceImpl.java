package com.ssafy.hallhole.performance.service;

import com.ssafy.hallhole.performance.domain.DetailPerformance;
import com.ssafy.hallhole.performance.domain.Facility;
import com.ssafy.hallhole.performance.domain.Performance;
import com.ssafy.hallhole.performance.repository.PerformanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PerformanceServiceImpl implements PerformanceService {

    private final PerformanceRepository performanceRepository;

    @Override
    public List<Performance> getPerformances(int start, int size) {
        return performanceRepository.findAllPerformancePaging(start, size);
    }

    @Override
    public List<Facility> getFacilities(int start, int size) {
        return performanceRepository.findAllFacilityPaging(start, size);
    }

    @Override
    public Performance findOnePerformance(String id) {
        return performanceRepository.findOnePerformanceById(id);
    }

    @Override
    public DetailPerformance getDetail(String id) {
        return performanceRepository.findOneDetailPerformance(id);
    }

    @Override
    public Facility getOneFacility(String id) {
        return performanceRepository.findOneFacility(id);
    }

    @Override
//    @Scheduled(cron = "")
    public void scheduledOpenAndCloseChat() {
        //todo 당일 오픈되는 공연과 닫히는 공연 체크 후 채팅방 생성 / 소멸 시키기
        List<Performance> runningPerformances = performanceRepository.findRunningPerformances();
        for (int i = 0; i < runningPerformances.size(); i++) {
            //채팅방 없으면 생성
            System.out.println(runningPerformances.get(i));

            //채팅방 리스트 불러와서 날짜가 지났으면 삭제

        }
    }

    @Override
    public void likePerformance(String performance_id, String member_id) {
        //todo 좋아요 추가
        // member 통합 후 추가
    }

    @Override
    public void unLikePerformance(String performance_id, String member_id) {
        //todo 좋아요 취소
        // member 통합 후 추가

    }

    @Override
    public List<Performance> findPerformancesByName(int start, int size, String name) {
        return performanceRepository.findPerformancesByNamePaging(start, size, name);
    }

    @Override
    public List<Facility> findFacilitiesByName(int start, int size, String name) {
        return performanceRepository.findFacilitiesByName(start, size, name);
    }

}
