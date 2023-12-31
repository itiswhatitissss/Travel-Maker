package org.zerock.travelmaker.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.zerock.travelmaker.domain.*;
import org.zerock.travelmaker.dto.PartyDTO;
import org.zerock.travelmaker.dto.PlanDTO;
import org.zerock.travelmaker.dto.UserPartyDTO;
import org.zerock.travelmaker.mapper.MybatisMapper;
import org.zerock.travelmaker.repository.*;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.util.*;

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
    private final PartyRepository partyRepository;
    private final UserPartyRepository userPartyRepository;
    private final UserRepository userRepository;


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
    public Long getPartyOne(Long pno) {
        Long result = mybatisMapper.getPartyOne(pno);
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
        Gallery gallery = Gallery.builder().plnoByGallery(byplno).build();
        galleryRepository.save(gallery);
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

    @Override
    public void PartyRegister(PartyDTO partyDTO, List<Long> member) {
        Party party = modelMapper.map(partyDTO, Party.class);
        partyRepository.save(party);
        Long pno2= party.getPno();

        for(int i=0;i< member.size();i++) {
            Optional<Users> byId = userRepository.findById(member.get(i));
            Users uno = byId.orElseThrow();
            Optional<Party> byId2 = partyRepository.findById(pno2);
            Party pno = byId2.orElseThrow();

            UserParty userParty = UserParty.builder()
                    .pnoByUserParty(pno)
                    .unoByUserParty(uno)
                    .build();

            userPartyRepository.save(userParty);
        }//문제!! controller에서 내 uno를 member List에 넣어줘야함
    }

    @Override
    public void deletePlan(Long plno) {
        planRepository.deleteById(plno);
    }

    @Override
    public void deleteParty(Long uno, Long pno) {
        Optional<Users> byId = userRepository.findById(uno);
        Users uno1 = byId.orElseThrow();
        Optional<Party> byId2 = partyRepository.findById(pno);
        Party pno1 = byId2.orElseThrow();

        userPartyRepository.partyDelete(uno1, pno1);
    }

    @Override
    public List<Map<String, Object>> getPlanOne(Long pno, Long plno, Long uno) {
        List<Map<String, Object>> result = mybatisMapper.getPlanOne(pno, plno, uno);
        return result;
    }

    @Override
    public List<String> searchPartyByName(String keyword, Long uno) {
        log.info("keyword=============="+keyword);
        log.info("uno================="+uno);
        List<String> partyList = mybatisMapper.searchParty(keyword, uno);
        return partyList;
    }

    @Override
    public Long searchPartyOne(String partyname) {
        Long pno=mybatisMapper.searchPartyOne(partyname);
        return pno;
    }
    @Override
    public List<Map<String, Object>> getPartymodifyView(Long pno) {
        List<Map<String, Object>> list =mybatisMapper.partymodifyView(pno);

        return list;
    }
    @Override
    public void userPartySave(Long pno, Long fno) {
        Optional<Users> byId = userRepository.findById(fno);
        Users uno = byId.orElseThrow();
        Optional<Party> byId2 = partyRepository.findById(pno);
        Party pno1 = byId2.orElseThrow();

        UserParty userParty= UserParty.builder()
                .pnoByUserParty(pno1)
                .unoByUserParty(uno)
                .build();

        userPartyRepository.save(userParty);
    }
    @Override
    public void modifyParty(String name, Long pno) {
        partyRepository.updateParty(name, pno);
    }

}
