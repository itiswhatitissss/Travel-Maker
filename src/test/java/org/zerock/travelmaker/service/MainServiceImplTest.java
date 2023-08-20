package org.zerock.travelmaker.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.travelmaker.domain.Plan;
import org.zerock.travelmaker.dto.PlanDTO;
import org.zerock.travelmaker.repository.PlanRepository;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

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
                    .start(startDate.toLocalDate())
                    .end(endDate.toLocalDate())
                    .build();

            Long plno = mainService.planRegister(planDTO);
            log.info("plno :" + plno);

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testReadOne(){
        PlanDTO planDTO = mainService.readOne(1L);
        log.info("planDTO : " + planDTO);
    }

}