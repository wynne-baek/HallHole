package com.ssafy.hallhole.review;

import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReactionCnt {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id")
    private Review review;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reactiontype_id")
    private ReactionType reactiontype;

    @NotNull
    @Builder.Default
    @ColumnDefault("1")
    private int reactionCnt = 1;


    public void addReaction() {
        this.reactionCnt++;
        return;
    }

    public void subReaction() {
        this.reactionCnt--;
        return;
    }
}
