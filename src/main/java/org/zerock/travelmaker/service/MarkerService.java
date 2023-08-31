package org.zerock.travelmaker.service;

import org.zerock.travelmaker.domain.Plan;
import org.zerock.travelmaker.dto.MarkerDTO;

import java.util.List;

public interface MarkerService {
    Plan LongToPlan(Long plno);
    List<MarkerDTO> getMarkersByPlno(Long plno);
    void deleteMarker(Long plno);
}
