package com.ssafy.hallhole.performance.repository;

import com.ssafy.hallhole.performance.domain.DetailPerformance;
import com.ssafy.hallhole.performance.domain.Facility;
import com.ssafy.hallhole.performance.domain.Performance;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PerformanceRepositoryImpl implements PerformanceRepository{

    private final EntityManager em;

    @Override
    public void save(Performance performance) {
        em.persist(performance);
    }

    @Override
    public void saveDetail(DetailPerformance detailPerformance) {
        em.persist(detailPerformance);
    }

    @Override
    public void saveFacility(Facility facility) {
        em.persist(facility);
    }

    @Override
    public List<Performance> findAllPerformance() {
        return em.createQuery("select p from Performance p").getResultList();
    }

    @Override
    public Performance findOnePerformanceById(String id) {
        return em.find(Performance.class,id);
    }

    @Override
    public DetailPerformance findOneDetailPerformance(String id) {
        return em.find(DetailPerformance.class,id);
    }

    @Override
    public List<String> findAllFacility() {
        return em.createQuery("select distinct(f.facility_name) from Performance f").getResultList();
    }

    @Override
    public Facility findOneFacility(String id) {
        return em.find(Facility.class,id);
    }
}
