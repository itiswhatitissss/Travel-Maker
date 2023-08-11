package org.zerock.travelmaker.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "galleryPK")
public class Gallery{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="galleryPK")
    private Long galleryPK;

    @ManyToOne
    @JoinColumn(name = "plno")
    private Plan plnoByGallery;

    private String image;
}
