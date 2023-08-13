package org.zerock.travelmaker.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VoteOptionDTO {

    private Long voteoptionPK;
    private Long vnoByVoteOption;
    private Long unoByVoteOption;
    private String voOption;
}
