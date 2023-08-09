package org.zerock.travelmaker.repository;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.travelmaker.domain.Party;
import org.zerock.travelmaker.domain.User;
import org.zerock.travelmaker.dto.PartyDTO;

import java.util.Optional;

@SpringBootTest
@Log4j2
class TestRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PartyRepository partyRepository;

    @Test
    public void testUser(){
        User user = User.builder().
                id("dddd").name("이하림").password("leehal").email("gkdms").address("ss").party(partyRepository.findById(1L).get()).build();

        User result = userRepository.save(user);
    }

    @Test
    public void testParty(){
        Party party = Party.builder().partyName("강릉고고").user(userRepository.findById(1L).get()).build();

        Party result = partyRepository.save(party);
    }

    @Test
    void Loads(){

    }

}