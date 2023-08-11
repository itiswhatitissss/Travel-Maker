package org.zerock.travelmaker.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "partyDetailPK")
public class PartyDetail{

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long partyDetailPK;

    @ManyToOne
    @JoinColumn(name = "plno")
    private Plan plnoByPartyDetail;

    @ManyToOne
    @JoinColumn(name = "pno")
    private Party pnoByPartyDetail;

}
