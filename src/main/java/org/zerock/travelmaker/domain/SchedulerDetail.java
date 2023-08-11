package org.zerock.travelmaker.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "schDetailPK")
public class SchedulerDetail {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="schDetailPK")
    private Long schDetailPK;

    @ManyToOne
    @JoinColumn(name="sno")
    private Scheduler snoBySchedulerDetail;

    private String content;
    private Date startTime;
    private Date endTime;


}
