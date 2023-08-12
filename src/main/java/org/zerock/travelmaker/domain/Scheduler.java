package org.zerock.travelmaker.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "sno")
public class Scheduler{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sno;

    @OneToOne
    @JoinColumn(name="plno")
    private Plan plnoByScheduler;

    @OneToMany(mappedBy = "snoBySchedulerDetail")
    private List<SchedulerDetail> schedulerDetails = new ArrayList<>();

}
