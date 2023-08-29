package org.zerock.travelmaker.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "AttendPK")
public class Attend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long AttendPK;

    @ManyToOne
    @JoinColumn(name = "uno",referencedColumnName = "uno")
    private Users unoByAttend;

    @ManyToOne
    @JoinColumn(name = "plno",referencedColumnName = "plno")
    private Plan plnoByAttend;

    private Long Attender;
}
