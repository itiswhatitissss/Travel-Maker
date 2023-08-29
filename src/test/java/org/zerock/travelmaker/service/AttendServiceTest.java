package org.zerock.travelmaker.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
@Slf4j
public class AttendServiceTest {
    @Autowired
    private AttendService attendService;

    @Test
    public void testdoAttend(){
        Long uno=3L;
        Long plno=2L;
        Long attend=1L;
        attendService.doAttend(uno,plno,attend);
    }
    @Test
    public void testmodifyAttend(){
        Long uno=3L;
        Long plno=2L;
        Long attend=0L;
        attendService.modifyAttend(uno,plno,attend);
    }
    @Test
    public void testListAttend(){
        Long plno=1L;
        List<Map<String, Object>> list = attendService.listAttend(plno);
        log.info("참가자 명단은 : "+list);
    }
}
