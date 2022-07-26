package com.ssafy.hallhole.review;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Getter
@Builder
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
public class ReactionCnt {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id")
    private Review review;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reaction_type_id")
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
