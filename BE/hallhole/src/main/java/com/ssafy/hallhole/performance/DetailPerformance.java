package com.ssafy.hallhole.performance;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Getter
@Builder
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
public class DetailPerformance {

    @Id
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "performance_id")
    private Performance performance;

    private String actor;

    private String runtime;

    private String productionCompany;

    @Column(columnDefinition = "INT UNSIGNED")
    private String price;

    @OneToMany(mappedBy = "performance")
    private List<PerformanceImage> images = new LinkedList<>();
}
