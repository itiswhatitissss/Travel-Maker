package org.zerock.travelmaker.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GalleryDTO {

    private Long galleryPK;
    private String title;
    private String filename;
    private String filepath;
    private Long plnoByGallery;
}
