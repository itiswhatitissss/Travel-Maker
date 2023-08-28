package org.zerock.travelmaker.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "sno")
public class Scheduler{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sno;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="plno")
    private Plan plnoByScheduler;

    @OneToMany(mappedBy = "snoBySchedulerDetail", cascade = CascadeType.ALL)
    private List<SchedulerDetail> schedulerDetails = new ArrayList<>();

}
