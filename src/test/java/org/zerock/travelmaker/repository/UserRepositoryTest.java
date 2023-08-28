package org.zerock.travelmaker.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.travelmaker.service.MainService;


@SpringBootTest
@Slf4j
class UserRepositoryTest {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MainService mainService;

    @Test
    public void getUNO(){
        String id = "pkmm";
        Long result = userRepository.findUno(id);
        log.info("result.......>>{}", result);
    }

    @Test
    public void Test(){
//        mainService.autocomplete("ㅎ");

    }
}