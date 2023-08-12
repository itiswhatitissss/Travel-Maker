package org.zerock.travelmaker.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "schdetailPK")
public class SchedulerDetail {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long schdetailPK;

    @ManyToOne
    @JoinColumn(name="sno")
    private Scheduler snoBySchedulerDetail;

    private String content;

    private Date startTime;

    private Date endTime;



}
