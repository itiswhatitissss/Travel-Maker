package org.zerock.travelmaker.service;

import org.zerock.travelmaker.domain.SchedulerDetail;
import org.zerock.travelmaker.dto.SchedulerDetailDTO;

import java.util.Date;
import java.util.List;

public interface SchedulerService {

    Long registerScheduler(SchedulerDetailDTO schedulerDetailDTO);

    List<SchedulerDetailDTO> listScheduler();

    SchedulerDetailDTO detail(Long schdetailPK);

    void updateScheduler(SchedulerDetailDTO schedulerDetailDTO);

    void deleteScheduler(Long schdetailPK);
}
