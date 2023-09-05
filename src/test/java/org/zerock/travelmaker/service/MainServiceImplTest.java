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
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@SpringBootTest
@Log4j2
class MainServiceImplTest {

    @Autowired
    private MainService mainService;
    @Autowired
    private FriendService friendService;

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
    @Test
    public void testModifyParty(){
        mainService.modifyParty("다시!!!",3L);
    }
    @Test
    public void insertUserParty(){
        mainService.userPartySave(3L,1L);
    }
    @Test
    public void getListPartyModify(){

        Long pno = 2L;
        Long uno = 1L;
        List<Map<String, Object>> partylist = mainService.getPartymodifyView(pno);
        List<Map<String, Object>> friendlist = friendService.friendList(uno);

        Iterator<Map<String, Object>> partyIterator = partylist.iterator();
        while (partyIterator.hasNext()) {
            Map<String, Object> party = partyIterator.next();
            String partyMember = (String) party.get("member");

            Iterator<Map<String, Object>> friendIterator = friendlist.iterator();
            while (friendIterator.hasNext()) {
                Map<String, Object> friend = friendIterator.next();
                String friendName = (String) friend.get("name");

                if (partyMember != null && partyMember.equals(friendName)) {
                    friendIterator.remove();
                    log.info("친구 리스트에서 삭제: " + friendName);
                }
            }
        }

        partylist.addAll(friendlist);
        log.info("리스트는: " + partylist);
        log.info("친구 리스트는: " + friendlist);

    }

}