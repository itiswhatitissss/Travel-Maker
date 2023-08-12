package org.zerock.travelmaker.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "pno")
public class Party{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pno")
    private Long pno;

    private String partyName;
    private String QR;

    @OneToMany(mappedBy = "pnoByUserParty")
    private List<UserParty> userParties = new ArrayList<>();

    @OneToMany(mappedBy = "pnoByPartyDetail")
    private List<PartyDetail> partyDetails = new ArrayList<>();

}
