package org.zerock.travelmaker.domain;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "plno")
public class Plan{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="plno")
    private Long plno;

    @Column(name="start", updatable = true)
    private Date start;

    @Column(name="end", updatable = true)
    private Date end;

    @Column(length = 50, nullable = false)
    private String title;

    @OneToMany(mappedBy = "plnoByPartyDetail")
    private List<PartyDetail> partyDetails = new ArrayList<>();

    @OneToMany(mappedBy = "plnoByGallery")
    private List<Gallery> galleries = new ArrayList<>();

    @OneToOne(mappedBy = "plnoByScheduler")
    private Scheduler scheduler;

    @OneToOne(mappedBy = "plnoByVote")
    private Vote vote;
}
