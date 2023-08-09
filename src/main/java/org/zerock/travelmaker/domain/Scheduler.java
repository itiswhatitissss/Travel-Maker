package org.zerock.travelmaker.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "SchPlno")
public class Scheduler implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="plno")
    private Long SchPlno;

    @ManyToOne
    @JoinColumn(name="start")
    private Plan SchStart;

    @ManyToOne
    @JoinColumn(name="end")
    private Plan SchEnd;
    @Column(length = 500)
    private String content;
}
