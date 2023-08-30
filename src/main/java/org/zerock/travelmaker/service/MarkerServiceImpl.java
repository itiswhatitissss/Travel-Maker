package org.zerock.travelmaker.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.travelmaker.domain.Plan;
import org.zerock.travelmaker.repository.PlanRepository;

import javax.transaction.Transactional;
import java.util.Optional;
@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class MarkerServiceImpl implements MarkerService{

    private final PlanRepository planRepository;

    @Override
    public Plan LongToPlan(Long plno) {
        Optional<Plan> plan = planRepository.findById(plno);
        Plan result = plan.orElseThrow();
        log.info("result : "+ result.getPlno());
        return result;
    }
}
