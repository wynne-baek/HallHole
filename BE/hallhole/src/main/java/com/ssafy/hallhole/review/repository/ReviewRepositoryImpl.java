package com.ssafy.hallhole.review.repository;

import com.ssafy.hallhole.advice.exceptions.NotFoundException;
import com.ssafy.hallhole.review.domain.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepository{

    private final EntityManager em;

    @Override
    public void save(Review review) {
        em.persist(review);
    }

    @Override
    public Review findOneReviewById(Long id) throws NotFoundException {
        return em.createQuery("select r from Review r where r.isDelete=:is_delete and r.id=:reviewId", Review.class)
                .setParameter("is_delete", false)
                .setParameter("reviewId", id)
                .getSingleResult();
    }

    @Override
    public List<Review> findAllByMemberId(Long memberId){
        return em.createQuery("select r from Review r where r.isDelete=:is_delete and r.member.id = :member_id",Review.class)
                .setParameter("is_delete",false)
                .setParameter("member_id",memberId)
                .getResultList();
    }

    @Override
    public List<Review> findAllByPerformanceId(String performanceId){
        return em.createQuery("select r from Review r where r.isDelete=:is_delete and r.performance.id=:pId",Review.class)
                .setParameter("is_delete",false)
                .setParameter("pId",performanceId)
                .getResultList();
    }

    @Override
    public List<Review> findAllReviewPagingByMemberId(int start, int size, Long memberId) {
        return em.createQuery("select r from Review r where r.isDelete=:is_delete and r.member.id=:member_id order by r.updateTime desc ",Review.class)
                .setFirstResult(start)
                .setMaxResults(size)
                .setParameter("is_delete",false)
                .setParameter("member_id",memberId)
                .getResultList();
    }

    @Override
    public List<Review> findAllReviewPagingByPerformanceId(int start, int size, String performanceId) {
        return em.createQuery("select r from Review r where r.isDelete=:is_delete and r.performance.id=:pId order by r.updateTime desc ",Review.class)
                .setFirstResult(start)
                .setMaxResults(size)
                .setParameter("is_delete",false)
                .setParameter("pId",performanceId)
                .getResultList();
    }


}
