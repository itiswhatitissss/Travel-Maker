package org.zerock.travelmaker.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.zerock.travelmaker.domain.Party;
import org.zerock.travelmaker.domain.Plan;
import org.zerock.travelmaker.domain.Vote;
import org.zerock.travelmaker.domain.VoteOption;
import org.zerock.travelmaker.dto.VoteDTO;
import org.zerock.travelmaker.repository.PartyRepository;
import org.zerock.travelmaker.repository.PlanRepository;
import org.zerock.travelmaker.repository.VoteOptionRepository;
import org.zerock.travelmaker.repository.VoteRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class VoteServiceImpl implements VoteService {
    private final PartyRepository partyRepository;
    private final VoteRepository voteRepository;
    private final PlanRepository planRepository;
    private final VoteOptionRepository voteOptionRepository;
    @Override
    public void voteInsert(VoteDTO voteDTO, List<String> option, Long pno) {
        Optional<Party> byId = partyRepository.findById(pno);
        Party party = byId.orElseThrow();

        Long plan = voteDTO.getPlnoByVote();
        Optional<Plan> byId2 = planRepository.findById(plan);
        Plan plno= byId2.orElseThrow();

        String title = voteDTO.getVTitle();
        int check = voteDTO.getVCheck();
        int complete = 0;
        int count = voteRepository.countMember(party);

        Vote vote1 = Vote.builder()
                .plnoByVote(plno)
                .vCheck(check)
                .vComplete(complete)
                .vCount(count)
                .vTitle(title)
                .vEndDate(plno.getEnd())
                .build();
        Vote vote = voteRepository.save(vote1);

        Optional<Vote> byId3 = voteRepository.findById(vote.getVno());
        Vote vno = byId3.orElseThrow();

        for (int i=0;i<option.size();i++){
            String vtOption = option.get(i);
            VoteOption voteOption = VoteOption.builder()
                    .voOption(vtOption)
                    .vnoByVoteOption(vno)
                    .build();
            voteOptionRepository.save(voteOption);
        }
    }
}
