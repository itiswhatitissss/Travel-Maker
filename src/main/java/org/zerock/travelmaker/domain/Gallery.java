package org.zerock.travelmaker.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "galleryPK")
public class Gallery{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long galleryPK;

    @Column(length = 1000)
    private String filename;
    @Column(length = 1000)
    private String filepath;

    @ManyToOne
    @JoinColumn(name="plno")
    private Plan plnoByGallery;
}