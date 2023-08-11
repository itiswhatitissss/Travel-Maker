package org.zerock.travelmaker.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "userpartyPK")
public class UserParty {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userpartyPK;

    @ManyToOne
    @JoinColumn(name = "uno",referencedColumnName = "uno")
    private User unoByUserParty;

    @ManyToOne
    @JoinColumn(name = "pno",referencedColumnName = "pno")
    private Party pnoByUserParty;


}
