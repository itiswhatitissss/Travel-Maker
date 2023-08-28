package org.zerock.travelmaker.service;

import org.zerock.travelmaker.domain.Party;
import org.zerock.travelmaker.domain.Plan;
import org.zerock.travelmaker.dto.PartyDTO;
import org.zerock.travelmaker.dto.PlanDTO;
import org.zerock.travelmaker.dto.UserPartyDTO;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface MainService {
    List<Map<String,Object>> getParty(Long uno);

    List<Map<String,Object>> getPlan(Long pno);

    Long getPartyOne(Long pno);

    PlanDTO readOne(Long plno);

    void planRegister(PlanDTO planDTO, Long pno);

    Party findByPno(Long pno); //for planRegister

    Plan findByPlno(Long plno); //for planRegister

    void PartyRegister(PartyDTO partyDTO, List<Long> member);

    void deletePlan(Long plno);

    void deleteParty(Long uno, Long pno);

    List<Map<String, Object>> getPlanOne(Long pno, Long plno);

    List<Map<String, Object>> searchPartyByName(String value);
}
