package org.zerock.travelmaker.service;

import org.zerock.travelmaker.domain.Party;
import org.zerock.travelmaker.domain.Plan;
import org.zerock.travelmaker.dto.PlanDTO;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface MainService {
    List<Map<String,Object>> getParty(Long uno);

    List<Map<String,Object>> getPlan(Long pno);

    PlanDTO readOne(Long plno);

    void planRegister(PlanDTO planDTO, Long pno);

    Party findByPno(Long pno); //for planRegister

    Plan findByPlno(Long plno); //for planRegister


}
