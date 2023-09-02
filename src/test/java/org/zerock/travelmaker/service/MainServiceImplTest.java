package org.zerock.travelmaker.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.travelmaker.domain.Plan;
import org.zerock.travelmaker.dto.PartyDTO;
import org.zerock.travelmaker.dto.PlanDTO;
import org.zerock.travelmaker.repository.PlanRepository;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootTest
@Log4j2
class MainServiceImplTest {

    @Autowired
    private MainService mainService;

    @Test
    public void testplanRegister() {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date startDate = new Date(dateFormat.parse("2022-08-20").getTime());
            Date endDate = new Date(dateFormat.parse("2022-08-21").getTime());

            PlanDTO planDTO = PlanDTO.builder()
                    .title("새로운 여행")
                    .start(startDate)
                    .end(endDate)
                    .build();
            Long pno = 2L;
            mainService.planRegister(planDTO, pno);

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testReadOne(){
        PlanDTO planDTO = mainService.readOne(1L);
        log.info("planDTO : " + planDTO);
    }
    @Test
    public void testPatryRegister(){
        List<Long> member = new ArrayList<Long>();
        member.add(1L);
        member.add(2L);
        member.add(3L);

        PartyDTO partyDTO =PartyDTO.builder()
                .partyName("한신ㄱ")
                .build();

        mainService.PartyRegister(partyDTO,member);
    }
    @Test
    public void testccCalendar(){
        Long pno =2L;
        List<Map<String, Object>> list = mainService.readCalendar(pno);
        log.info("달력보이기 : ",list);
    }

}