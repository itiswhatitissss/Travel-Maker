package org.zerock.travelmaker.mapper;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.travelmaker.domain.Plan;
import org.zerock.travelmaker.repository.PlanRepository;
import org.zerock.travelmaker.service.UserService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@SpringBootTest
@Log4j2
public class MapperTests {

    @Autowired
    private MybatisMapper mybatisMapper;
    @Autowired
    private PlanRepository planRepository;

    @Autowired
    private UserService loginService;

//    @Test
//    public void testGetList() {
////        List<SampleDomain> list = sampleMapper.getlist();
////        for(BoardVO vo : list)
////            log.info("vo : {}",vo);
//        sampleMapper.getBoards().forEach((vo) -> {
//            log.info("vo : {}", vo);
//        });
//    }

    @Test
    public void testGetPartyList(){ //파티 리스트
        List<Map<String,Object>> result =mybatisMapper.getPartyList(1L);
        log.info("resutl=================>"+ result);
    }

    @Test
    public void testGetPlanList(){ //플랜 리스트
        List<Map<String,Object>> result = mybatisMapper.getPlanList(2L);
        log.info("result==============>"+ result);
    }

    @Test
    public void testGetUser(){
        Long result = loginService.getUno("pkmm");
        log.info("resutl=================>"+ result);
    }
    @Test
    public void testFriendList(){
        List<Map<String,Object>> result = mybatisMapper.selectFriendList(1L);
        log.info("result==============>"+ result);
    }
    @Test
    public void testFriendSearch(){
        List<Map<String,Object>> result = mybatisMapper.searchFriend("leehal");
        log.info("result==============>"+ result);
    }

    @Test
    public void testGetPartyOne(){
        Long result = mybatisMapper.getPartyOne(1L);
        log.info("result========>"+result);
    }
    @Test
    public void testListAttend(){
        Optional<Plan> byId2 = planRepository.findById(1L);
        Plan pplno = byId2.orElseThrow();
        List<Map<String, Object>> list =mybatisMapper.selectAttendList(pplno);

        log.info("참가자 리스트 : "+list);
    }

}