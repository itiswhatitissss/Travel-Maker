package org.zerock.travelmaker.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "vno")
public class Vote{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vno;

}
