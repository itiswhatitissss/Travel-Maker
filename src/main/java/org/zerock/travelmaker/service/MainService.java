package org.zerock.travelmaker.service;

import org.zerock.travelmaker.dto.PlanDTO;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface MainService {
    List<Map<String,Object>> getParty(Long uno);

    //plan
    Long planRegister(PlanDTO planDTO);

    PlanDTO readOne(Long plno);

}
