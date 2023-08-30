package org.zerock.travelmaker.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.zerock.travelmaker.domain.Attend;
import org.zerock.travelmaker.domain.Plan;
import org.zerock.travelmaker.domain.Users;
import org.zerock.travelmaker.mapper.MybatisMapper;
import org.zerock.travelmaker.repository.AttendRepository;
import org.zerock.travelmaker.repository.PlanRepository;
import org.zerock.travelmaker.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class AttendServiceImpl implements AttendService{
    private final AttendRepository attendRepository;
    private final UserRepository userRepository;
    private final PlanRepository planRepository;
    private final MybatisMapper mybatisMapper;

    @Override
    public void doAttend(Long uno, Long plno, Long attender) {
        Optional<Users> byId = userRepository.findById(uno);
        Users uuno = byId.orElseThrow();
        Optional<Plan> byId2 = planRepository.findById(plno);
        Plan pplno = byId2.orElseThrow();

        Attend attend = Attend.builder()
                .Attender(attender)
                .unoByAttend(uuno)
                .plnoByAttend(pplno)
                .build();

        attendRepository.save(attend);
    }

    @Override
    public void modifyAttend(Long uno, Long plno, Long attender) {
        Optional<Users> byId = userRepository.findById(uno);
        Users uuno = byId.orElseThrow();
        Optional<Plan> byId2 = planRepository.findById(plno);
        Plan pplno = byId2.orElseThrow();

        Long result = attendRepository.selectAttend(uuno,pplno);
        attendRepository.updateAttend(uuno,pplno,result,attender);
    }

    @Override
    public List<Map<String, Object>> listAttend(Long plno) {
        Optional<Plan> byId2 = planRepository.findById(plno);
        Plan pplno = byId2.orElseThrow();
        List<Map<String, Object>> list =mybatisMapper.selectAttendList(pplno);

        return list;
    }

    @Override
    public Long checkAttend(Long uno, Long plno) {
        Optional<Users> byId = userRepository.findById(uno);
        Users uuno = byId.orElseThrow();

        Optional<Plan> byId2 = planRepository.findById(plno);
        Plan pplno = byId2.orElseThrow();

        Long result= attendRepository.selectAttend(uuno,pplno);

        return result;
    }
}
