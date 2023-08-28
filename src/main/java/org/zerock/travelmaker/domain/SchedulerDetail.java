package org.zerock.travelmaker.domain;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalTime;
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

    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime startTime;

    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime endTime;



}
