package org.zerock.travelmaker.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PartyDetailDTO {

    private Long partydetailPK;
    private Long pnoByPartyDetail;
    private Long plnoByPartyDetail;
}
