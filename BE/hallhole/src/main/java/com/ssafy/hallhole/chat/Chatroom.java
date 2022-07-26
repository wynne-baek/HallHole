package com.ssafy.hallhole.chat;

import com.ssafy.hallhole.performance.Performance;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Chatroom {
    @Id
    @JoinColumn(name="performance_id")
    @OneToOne(fetch = FetchType.LAZY)
    private Performance performance;

    @NotNull
    @Column(length = 50)
    private String name;

    @NotNull
    @Builder.Default
    @ColumnDefault("0")
    private int userCount = 0;

    @NotNull
    private LocalDateTime openTime;

    @NotNull
    private LocalDateTime closeTime;


    public void addUser(){
        this.userCount++;
        return;
    }
    public void subUser(){
        this.userCount--;
        return;
    }
}
