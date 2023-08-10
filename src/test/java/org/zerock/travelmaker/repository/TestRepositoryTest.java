package org.zerock.travelmaker.repository;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.travelmaker.domain.Party;
import org.zerock.travelmaker.domain.User;
import org.zerock.travelmaker.domain.UserLogin;

import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
@Log4j2
class TestRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PartyRepository partyRepository;
    @Autowired
    private UserLoginRepository userLoginRepository;

    @Test
    public void test1(){
        UserLogin userLogin = UserLogin.builder().id("pkmm").name("이성진").password("1234").email("naver@").address("화서동").phone("0103505").build();
        userLoginRepository.save(userLogin);

        Party party = Party.builder().partyName("양양가자").userLogin(userLogin).build();
        partyRepository.save(party);

        User user = User.builder().party1(party).party2(party).build();
        userRepository.save(user);


    }
    @Test
    public void test2(){
        UserLogin userLogin = UserLogin.builder().id("leehal").name("이하림").password("1234").email("naver@").address("화서동").phone("0102222").build();
        userLoginRepository.save(userLogin);
        Party party = partyRepository.findById(1L).get();
    }
}