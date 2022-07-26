package com.ssafy.hallhole.performance;

import lombok.*;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
