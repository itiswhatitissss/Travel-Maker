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
public class SchedulerDetailDTO {

    private Long schdetailPK;
    private Long snoBySchedulerDetail;
    private String content;
    private Date startTime;
    private Date endTime;
}
