package org.zerock.travelmaker.service;

import org.zerock.travelmaker.dto.VoteDTO;

import java.util.List;
import java.util.Map;

public interface VoteService {
    void voteInsert(VoteDTO voteDTO, List<String > option, Long pno);
}
