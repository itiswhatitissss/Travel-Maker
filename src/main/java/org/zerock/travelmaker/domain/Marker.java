package org.zerock.travelmaker.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "markerPK")
public class Marker {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long markerPK;

    @ManyToOne
    @JoinColumn(name="plno")
    private Plan plnoByMarker;

    private double lat;
    private double lng;
    private String title;
}
