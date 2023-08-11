package org.zerock.travelmaker.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "voptionPK")
public class VoteOption {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="voptionPK")
    private Long voptionPK;

    @ManyToOne
    @JoinColumn(name="vno")
    private Vote vnoByVoteOption;

    @ManyToOne
    @JoinColumn(name="uno")
    private User unoByVoteOption;

    private String option;

}
