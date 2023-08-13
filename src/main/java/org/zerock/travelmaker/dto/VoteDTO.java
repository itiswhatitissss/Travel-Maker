package org.zerock.travelmaker.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VoteDTO {

    private Long vno;
    private Long pnoByVote;
    private Long plnoByVote;
    private String vTitle;
    private Date vEndDate;
    private int vCount;
    private int vComplete;
    private int vCheck;


}
