package org.zerock.travelmaker.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.travelmaker.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Slf4j
class LoginServiceImplTest {


    @Autowired
    private UserRepository userRepository;

    @Test
    public void getUNO(){
        String id = "pkmm";
        Long result = userRepository.findUno(id);
        log.info("result.......>>{}", result);

    }

}