package org.zerock.travelmaker.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "pno")
public class Party{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pno", nullable = false, columnDefinition = "int")
    private Long pno;

    @Column(length = 50,nullable = false)
    private String partyName;

    @ManyToOne(cascade =CascadeType.REMOVE)
    @JoinColumn(name = "uno", referencedColumnName = "uno")
    User user;

}
