package org.zerock.travelmaker.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.zerock.travelmaker.domain.*;
import org.zerock.travelmaker.dto.PlanDTO;
import org.zerock.travelmaker.mapper.MybatisMapper;
import org.zerock.travelmaker.repository.*;

import javax.swing.text.html.Option;
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
    private final PartyDetailRepository partyDetailRepository;
    private final PlanRepository planRepository;
    private final GalleryRepository galleryRepository;
    private final SchedulerRepositroy schedulerRepositroy;
    private final VoteRepository voteRepository;
    private final PartyRepository partyRepository;


    @Override
    public List<Map<String, Object>> getParty(Long uno) {
        List<Map<String,Object>> result = mybatisMapper.getPartyList(uno);
        return result;
    }

    @Override
    public List<Map<String, Object>> getPlan(Long pno) {
        List<Map<String,Object>> result = mybatisMapper.getPlanList(pno);
        return result;
    }


    @Override
    public PlanDTO readOne(Long plno) {
        Optional<Plan> result = planRepository.findById(plno);
        Plan plan = result.orElseThrow();
        PlanDTO planDTO = modelMapper.map(plan, PlanDTO.class);
        return planDTO;
    }


    @Override
    public void planRegister(PlanDTO planDTO, Long pno) {
        Plan plan = modelMapper.map(planDTO, Plan.class);
        Long plno =planRepository.save(plan).getPlno(); //plan 만들어주기
        Plan byplno =findByPlno(plno); //만들어진 plno값 받기
        Party byPno=findByPno(pno); //RequestParam 으로 받은 pno값 받기
        PartyDetail partyDetail = PartyDetail.builder().plnoByPartyDetail(byplno).pnoByPartyDetail(byPno).build();//partyDetail 따로 생성 why? prepersist로 생성하면 plno값만 던져주기 때문, pno도 필요
        partyDetailRepository.save(partyDetail);

        //갤러리,스케줄러,보트 plno값만 던져주고 나머지 null값으로 생성
//        Gallery gallery = Gallery.builder().plnoByGallery(byplno).build();
//        galleryRepository.save(gallery);
//        Scheduler scheduler = Scheduler.builder().plnoByScheduler(byplno).build();
//        schedulerRepositroy.save(scheduler);
//        Vote vote = Vote.builder().plnoByVote(byplno).build();
//        voteRepository.save(vote);
    }

    @Override
    public Party findByPno(Long pno) {
        Optional<Party> id = partyRepository.findById(pno);
        Party byPno = id.orElseThrow();
        return byPno;
    }

    @Override
    public Plan findByPlno(Long plno) {
        Optional<Plan> id = planRepository.findById(plno);
        Plan byPlno = id.orElseThrow();
        return byPlno;
    }


}
