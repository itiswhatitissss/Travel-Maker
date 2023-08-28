package org.zerock.travelmaker.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.zerock.travelmaker.domain.SchedulerDetail;
import org.zerock.travelmaker.dto.SchedulerDetailDTO;
import org.zerock.travelmaker.repository.ScheudlerDetailRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class SchedulerServiceImpl implements SchedulerService {

    private final ScheudlerDetailRepository scheudlerDetailRepository;

    @Override
    public Long registerScheduler(SchedulerDetailDTO schedulerDetailDTO) {
        Long sno = scheudlerDetailRepository.save(schedulerDetailDTO.toEntity()).getSchdetailPK();
        return sno;
    }

    @Override
    public List<SchedulerDetailDTO> listScheduler() {
        List<SchedulerDetail> list = scheudlerDetailRepository.findAll();
        List<SchedulerDetailDTO> dtoList = new ArrayList<>();

        for(SchedulerDetail detail : list){
            SchedulerDetailDTO schedulerDetailDTO = SchedulerDetailDTO.builder()
                    .schdetailPK(detail.getSchdetailPK())
                    .content(detail.getContent())
                    .startTime(detail.getStartTime())
                    .endTime(detail.getEndTime())
                    .build();

            dtoList.add(schedulerDetailDTO);
        }
        return dtoList;
    }

    @Override
    public SchedulerDetailDTO detail(Long schdetailPK) {
        Optional<SchedulerDetail> list = scheudlerDetailRepository.findById(schdetailPK);
        SchedulerDetail schedulerDetail = list.get();

        SchedulerDetailDTO schedulerDetailDTO = SchedulerDetailDTO.builder()
                .schdetailPK(schedulerDetail.getSchdetailPK())
                .content(schedulerDetail.getContent())
                .startTime(schedulerDetail.getStartTime())
                .endTime(schedulerDetail.getEndTime())
                .build();

        return schedulerDetailDTO;
    }

    @Override
    public void updateScheduler(SchedulerDetailDTO schedulerDetailDTO) {
        Optional<SchedulerDetail> list = scheudlerDetailRepository.findById(schedulerDetailDTO.getSchdetailPK());
        SchedulerDetail schedulerDetail = list.get();

        schedulerDetail.change(schedulerDetailDTO.getContent(), schedulerDetailDTO.getStartTime(), schedulerDetailDTO.getEndTime());
        scheudlerDetailRepository.save(schedulerDetail);
    }

    @Override
    public void deleteScheduler(Long schdetailPK) {
        scheudlerDetailRepository.deleteById(schdetailPK);
    }
}
