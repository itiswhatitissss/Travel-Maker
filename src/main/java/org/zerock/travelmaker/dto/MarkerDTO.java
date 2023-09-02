package org.zerock.travelmaker.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MarkerDTO {
    private Long markerPK;
    private Long plnoByMarker;
    private String title;
    private double lat;
    private double lng;
}
