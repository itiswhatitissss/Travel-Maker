package org.zerock.travelmaker.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date start;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date end;

    @Column(length = 50, nullable = false)
    private String title;

    @OneToMany(mappedBy = "plnoByPartyDetail", cascade = CascadeType.ALL)
    private List<PartyDetail> partyDetails = new ArrayList<>();

    @OneToMany(mappedBy = "plnoByGallery", cascade = CascadeType.ALL)
    private List<Gallery> galleries = new ArrayList<>();

    @OneToOne(mappedBy = "plnoByScheduler", cascade = CascadeType.ALL)
    private Scheduler scheduler;

    @OneToOne(mappedBy = "plnoByVote",cascade = CascadeType.ALL)
    private Vote vote;

//    @NotNull
//    private long planIdx;
//
//    @NotNull
//    private String originalFileName;
//
//    @NotNull
//    private String storedFileName;
//
//    private long fileSize;
}
