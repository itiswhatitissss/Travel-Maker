package org.zerock.travelmaker.mapper;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
@Log4j2
public class MapperTests {

    @Autowired
    private MybatisMapper mybatisMapper;

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
    public void testGetPartyList(){
        List<Map<String,Object>> result =mybatisMapper.getPartyList(1L);
        System.out.println(result);
    }
}
