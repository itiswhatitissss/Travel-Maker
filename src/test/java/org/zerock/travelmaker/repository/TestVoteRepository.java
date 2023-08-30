package org.zerock.travelmaker.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.travelmaker.domain.Party;
import org.zerock.travelmaker.domain.Users;

import java.util.Date;
import java.util.Optional;

@SpringBootTest
@Slf4j
public class TestVoteRepository {
    @Autowired
    private VoteRepository voteRepository;
    @Autowired
    private PartyRepository partyRepository;
    @Test
    public void CountTest(){
        Long pno =1L;
        Optional<Party> byId = partyRepository.findById(pno);
        Party party = byId.orElseThrow();
        int result =voteRepository.countMember(party);
        log.info("파티멤버수 : "+result);
    }
//    @Test
//    public void EndTest(){
//        Long plno =4L;
//        Date end =voteRepository.selectEnd(plno);
//        log.info("여행마지막날 : "+end);
//    }
}
