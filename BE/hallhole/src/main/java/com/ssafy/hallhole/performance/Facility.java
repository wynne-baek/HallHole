package com.ssafy.hallhole.performance;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Facility {

    @Id
    private String id;

    @NotNull
    private String name;

    private String addr;

    private Double lat;

    private Double lon;

}
