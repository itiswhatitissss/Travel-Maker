package org.zerock.travelmaker.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.zerock.travelmaker.domain.Plan;
import org.zerock.travelmaker.dto.PlanDTO;
import org.zerock.travelmaker.mapper.MybatisMapper;
import org.zerock.travelmaker.repository.PlanRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class MainServiceImpl implements MainService{

    private final MybatisMapper mybatisMapper;
    private final ModelMapper modelMapper;
    private final PlanRepository planRepository;

    @Override
    public List<Map<String, Object>> getParty(Long uno) {
        List<Map<String,Object>> result = mybatisMapper.getPartyList(uno);
        return result;
    }

    @Override
    public Long planRegister(PlanDTO planDTO) {
        Plan plan = modelMapper.map(planDTO, Plan.class);
        Long plno = planRepository.save(plan).getPlno();
        return plno;
    }

    @Override
    public PlanDTO readOne(Long plno) {
        Optional<Plan> result = planRepository.findById(plno);
        Plan plan = result.orElseThrow();
        PlanDTO planDTO = modelMapper.map(plan, PlanDTO.class);
        return planDTO;
    }


}
