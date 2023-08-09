package org.zerock.travelmaker.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "pdPno")
public class PartyDetail implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name = "pno")
    private Party pdPno;

    private String QR;

    @ManyToOne
    @JoinColumn(name = "plno")
    private Plan pdPlno;
}
