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

    private String title;
    private String filename;
    private String filepath;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="plno")
    private Plan plnoByGallery;
}
