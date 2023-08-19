package org.zerock.travelmaker.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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

    //    @Column(name="start", updatable = true)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate start;

    //    @Column(name="end", updatable = true)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate end;

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
