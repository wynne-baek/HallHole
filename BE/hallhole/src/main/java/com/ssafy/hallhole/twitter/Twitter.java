package com.ssafy.hallhole.twitter;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Getter
public class Twitter {

    @Id
    @Column(length = 25)
    private String id;

    @NotNull
    private String contents;

    @NotNull
    private LocalDateTime time;

}
