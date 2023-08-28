package org.zerock.travelmaker.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.zerock.travelmaker.domain.SchedulerDetail;

import java.time.LocalTime;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SchedulerDetailDTO {

    private Long schdetailPK;
    private Long snoBySchedulerDetail;
    private String content;
    private LocalTime startTime;
    private LocalTime endTime;

    public SchedulerDetail toEntity() {
        SchedulerDetail schedulerDetail = SchedulerDetail.builder()
                .schdetailPK(schdetailPK)
                .content(content)
                .startTime(startTime)
                .endTime(endTime)
                .build();

        return schedulerDetail;
    }
}
