package org.zerock.travelmaker.mapper;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class SampleMapperTests {

    @Autowired
    private SampleMapper sampleMapper;
    @Test
    public void testGetList() {
//        List<SampleDomain> list = sampleMapper.getlist();
//        for(BoardVO vo : list)
//            log.info("vo : {}",vo);
        sampleMapper.getBoards().forEach((vo) -> {
            log.info("vo : {}", vo);
        });
    }
}
