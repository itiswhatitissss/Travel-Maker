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
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "plno")
public class Plan{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="plno")
    private Long plno;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate  start;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate  end;

    @Column(length = 50, nullable = false)
    private String title;

    @OneToMany(mappedBy = "plnoByPartyDetail", cascade = CascadeType.ALL)
    private List<PartyDetail> partyDetails = new ArrayList<>();

    @OneToMany(mappedBy = "plnoByGallery", cascade = CascadeType.ALL)
    private List<Gallery> galleries = new ArrayList<>();

    @OneToMany(mappedBy = "plnoByAttend",cascade = CascadeType.ALL)
    private List<Attend> plnoAttend = new ArrayList<>();

    @OneToMany(mappedBy = "plnoByMarker",cascade = CascadeType.ALL)
    private List<Marker> marker;

    @Column(length = 1000)
    private String filename;

    @Column(length = 1000)
    private String filepath;

}
