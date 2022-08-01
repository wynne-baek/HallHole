package com.ssafy.hallhole.review.repository;

import com.ssafy.hallhole.review.domain.Review;
import com.ssafy.hallhole.review.dto.SummeryReviewDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Long> {

    @Query(value="select id, member_id, title, writingTime, star_eval from review where performance_id=:pid and is_delete=false", nativeQuery = true)
    List<SummeryReviewDTO> findAllByPerformanceId(@Param("pid") String pid);

}
