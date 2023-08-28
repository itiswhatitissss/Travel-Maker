package org.zerock.travelmaker.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.travelmaker.domain.Vote;
import org.zerock.travelmaker.dto.VoteDTO;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Slf4j
public class VoteServiceTest {
    @Autowired
    private VoteService voteService;
    @Test
    public void testVoteRegister(){
        VoteDTO voteDTO =new VoteDTO();
        voteDTO.setVTitle("투표실험");
        voteDTO.setPlnoByVote(1L);
        voteDTO.setVCheck(0);

        List<String> option = new ArrayList<String>();
        option.add("투표 옵션1");
        option.add("투표 옵션2");
        option.add("투표 옵션3");
        option.add("투표 옵션4");

        Long pno = 1L;

        voteService.voteInsert(voteDTO,option,pno);
    }
}
