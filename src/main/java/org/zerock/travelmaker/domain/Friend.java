package org.zerock.travelmaker.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "friendUno")
public class Friend implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name = "uno")
    private User friendUno;

    @ManyToOne
    @JoinColumn(name = "uno")
    private User fno;
}
