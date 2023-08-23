package org.zerock.travelmaker.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "vno")
public class Vote{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vno;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="pno")
    private Party pnoByVote;

    @OneToOne
    @JoinColumn(name="plno")
    private Plan plnoByVote;

    private String vTitle;
    private Date vEndDate;
    private int vCount;
    private int vComplete;
    private int vCheck;

    @OneToMany(mappedBy = "vnoByVoteOption")
    private List<VoteOption> voteOptions = new ArrayList<>();

}
