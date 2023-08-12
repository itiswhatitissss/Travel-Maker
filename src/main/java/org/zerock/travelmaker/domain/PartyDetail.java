package org.zerock.travelmaker.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "partydetailPK")
public class PartyDetail{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "partydetailPK")
    private Long partydetailPK;

    @ManyToOne
    @JoinColumn(name = "pno")
    private Party pnoByPartyDetail;

    @ManyToOne
    @JoinColumn(name = "plno")
    private Plan plnoByPartyDetail;
}
