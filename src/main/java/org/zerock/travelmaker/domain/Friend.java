package org.zerock.travelmaker.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "friendPK")
public class Friend implements Serializable {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long friendPK;

    @ManyToOne
    @JoinColumn(name = "uno",referencedColumnName = "uno")
    private User uno;

    @ManyToOne
    @JoinColumn(name = "fno",referencedColumnName = "uno")
    private User fno;
}
