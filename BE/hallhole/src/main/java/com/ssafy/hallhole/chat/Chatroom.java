package com.ssafy.hallhole.chat;

import com.ssafy.hallhole.performance.Performance;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
public class Chatroom implements Serializable {

    @Id
    @JoinColumn(name = "performance_id")
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

    public void addUser() {
        this.userCount++;
    }

    public void subUser() {
        this.userCount--;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Chatroom chatroom = (Chatroom) o;

        if (userCount != chatroom.userCount) return false;
        if (performance != null ? !performance.equals(chatroom.performance) : chatroom.performance != null)
            return false;
        if (name != null ? !name.equals(chatroom.name) : chatroom.name != null) return false;
        if (openTime != null ? !openTime.equals(chatroom.openTime) : chatroom.openTime != null) return false;
        return closeTime != null ? closeTime.equals(chatroom.closeTime) : chatroom.closeTime == null;
    }

    @Override
    public int hashCode() {
        int result = performance != null ? performance.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + userCount;
        result = 31 * result + (openTime != null ? openTime.hashCode() : 0);
        result = 31 * result + (closeTime != null ? closeTime.hashCode() : 0);
        return result;
    }
}
