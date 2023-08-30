package org.zerock.travelmaker.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.travelmaker.domain.Plan;
import org.zerock.travelmaker.dto.MarkerDTO;
import org.zerock.travelmaker.mapper.MybatisMapper;
import org.zerock.travelmaker.repository.MarkerRepository;
import org.zerock.travelmaker.repository.PlanRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class MarkerServiceImpl implements MarkerService{

    private final PlanRepository planRepository;
    private final MybatisMapper mybatisMapper;

    @Override
    public Plan LongToPlan(Long plno) {
        Optional<Plan> plan = planRepository.findById(plno);
        Plan result = plan.orElseThrow();
        log.info("result : "+ result.getPlno());
        return result;
    }

    @Override
    public List<MarkerDTO> getMarkersByPlno(Long plno) {
        List<MarkerDTO> result = mybatisMapper.getMarkersByPlno(plno);
        log.info("markerDTO =======> "+result.stream().toArray());
        return result;
    }

    @Override
    public void deleteMarker(Long plno) {
        mybatisMapper.deleteMarker(plno);
    }
}
