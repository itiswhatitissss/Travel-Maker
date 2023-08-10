package org.zerock.travelmaker.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "userPK")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userPK;

    @ManyToOne
    @JoinColumn(name = "uno",referencedColumnName = "uno")
    private Party party1;

    @ManyToOne
    @JoinColumn(name = "pno",referencedColumnName = "pno")
    private Party party2;


}
