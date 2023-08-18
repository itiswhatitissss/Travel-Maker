package org.zerock.travelmaker.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "voteoptionPK")
public class VoteOption {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long voteoptionPK;

    @ManyToOne
    @JoinColumn(name="vno")
    private Vote vnoByVoteOption;

    @ManyToOne
    @JoinColumn(name="uno")
    private Users unoByVoteOption;

    private String voOption;

}
