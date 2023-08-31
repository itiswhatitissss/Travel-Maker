package org.zerock.travelmaker.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "vno")
public class Vote{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vno;


    @OneToOne
    @JoinColumn(name="plno")
    private Plan plnoByVote;

    private String vTitle;
    private LocalDate vEndDate;
    private int vCount;
    private int vComplete;
    private int vCheck;

    @OneToMany(mappedBy = "vnoByVoteOption")
    private List<VoteOption> voteOptions = new ArrayList<>();

}
