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
    private Long galleryPK;

    private String image;

    @ManyToOne
    @JoinColumn(name="plno")
    private Plan plnoByGallery;
}
