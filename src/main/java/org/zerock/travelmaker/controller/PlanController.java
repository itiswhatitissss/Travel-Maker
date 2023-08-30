package org.zerock.travelmaker.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.zerock.travelmaker.domain.Marker;
import org.zerock.travelmaker.domain.Plan;
import org.zerock.travelmaker.domain.SchedulerDetail;
import org.zerock.travelmaker.domain.Users;
import org.zerock.travelmaker.dto.SchedulerDetailDTO;
import org.zerock.travelmaker.repository.MarkerRepository;
import org.zerock.travelmaker.service.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Log4j2
@Slf4j
@RequestMapping("/travelmaker/plan/*")
@RequiredArgsConstructor
public class PlanController {
    private final FriendService friendService;
    private final UserService userService;
    private final MainService mainService;
    private final SchedulerService schedulerService;
    private final AttendService attendService;
    private final MarkerRepository markerRepository;
    private final MarkerService markerService;

    @GetMapping("/planDetail")
    public void planDetailGET(@RequestParam("plno") Long plno, @RequestParam("pno") Long pno, Model model, Authentication authentication, HttpSession session) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        Long uno = userService.getUno(username);
        model.addAttribute("uno", uno);

        List<Map<String, Object>> planList = mainService.getPlanOne(pno, plno);
        model.addAttribute("planDTO",planList);

        List<Map<String,Object>> partyList = mainService.getParty(uno);
        model.addAttribute("partyDTO",partyList);

        List<Map<String,Object>> attendList = attendService.listAttend(plno);
        model.addAttribute("AttendDTO",attendList);
        model.addAttribute("plnno",plno);
        model.addAttribute("ppno",pno);

        Long attendCheck = attendService.checkAttend(uno,plno);
        if (attendCheck !=null){
            model.addAttribute("attendCheck",attendCheck);
            model.addAttribute("att",1);
        }else {
            model.addAttribute("att",0);
        }
        List<Map<String, Object>> friendDTO = (List<Map<String, Object>>) session.getAttribute("friendSearchResult");
        if (friendDTO == null) {
            List<Map<String,Object>> friend =friendService.friendList(uno);
            model.addAttribute("friendDTO",friend);
        } else {
            for(int i=0;i<friendDTO.size();i++) {
                Map<String, Object> userMap = friendDTO.get(0); // 첫 번째 검색 결과 사용
                // 필드 값 추출
                Long fno = (Long) userMap.get("uno");
                String name = (String) userMap.get("name");
                String id = (String) userMap.get("id");
                Users users = Users.builder()
                        .id(id)
                        .uno(fno)
                        .name(name)
                        .build();
                model.addAttribute("friendSearchResult", users);
            }
            List<Map<String, Object>> friend = friendService.friendList(uno);
            model.addAttribute("friendDTO", friend);
            session.removeAttribute("friendSearchResult"); // 세션에서 검색 결과 제거

        }
    };



    @PostMapping("/scheduler")
    public void schedulerPost() {}


    @GetMapping("/list")
    public void list(Model model) {
        List<SchedulerDetailDTO> list = schedulerService.listScheduler();
        model.addAttribute("detailList", list);
    }

    @GetMapping("/register")
    public void registerScheduler() {}

    @PostMapping("/register")
    public String registerScheduler(SchedulerDetailDTO schedulerDetailDTO){
        Long schdetailPK = schedulerService.registerScheduler(schedulerDetailDTO);

        return "redirect:/travelmaker/plan/list";
    }

    @GetMapping({"/detail", "/update"})
    public void detail(Long schdetailPK, Model model) {
        SchedulerDetailDTO schedulerDetailDTO = schedulerService.detail(schdetailPK);
        model.addAttribute("dto", schedulerDetailDTO);
    }


    @PostMapping("/update")
    public String updateScheduler(SchedulerDetailDTO schedulerDetailDTO){
        schedulerService.updateScheduler(schedulerDetailDTO);
        return "redirect:/travelmaker/plan/list";
    }

    @GetMapping("/delete")
    public String deleteScheduler(Long schdetailPK){
        schedulerService.deleteScheduler(schdetailPK);

        return "redirect:/travelmaker/plan/list";
    }
    @PostMapping("/attend")
    public String attendDo(@RequestParam("plno") Long plno, @RequestParam("pno") Long pno, Authentication authentication,@RequestParam(name = "attendance") Long attend){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        Long uno = userService.getUno(username);

        attendService.doAttend(uno,plno,attend);

        return "redirect:/travelmaker/plan/planDetail?plno="+plno+"&pno="+pno;
    }
    @PostMapping("/modifyattend")
    public String modifyAttend(@RequestParam("plno") Long plno, @RequestParam("pno") Long pno, Authentication authentication,@RequestParam(name = "attendance") Long attend){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        Long uno = userService.getUno(username);

        attendService.modifyAttend(uno,plno,attend);

        return "redirect:/travelmaker/plan/planDetail?plno="+plno+"&pno="+pno;
    }

    @PostMapping("/saveMarker")
    @ResponseBody
    public ResponseEntity<String> saveMarker(@RequestParam double lat, @RequestParam double lng, @RequestParam Long plno) {
        try {
            //Long값으로 받은 plno를 Plan으로 형변환
            Plan plan =markerService.LongToPlan(plno);
            log.info("lat : {}", lat);
            log.info("lng : {}", lng);
            log.info("plNo : {}", plno);
            // 받은 좌표로 마커 정보를 생성하여 저장
            Marker marker = Marker.builder().plnoByMarker(plan).lat(lat).lng(lng).build();
            log.info("marker : {}", marker.getPlnoByMarker().getPlno());
            markerRepository.save(marker);
            return ResponseEntity.ok("마커 좌표가 저장되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("마커 좌표 저장 중 오류가 .");
        }
    }
    @GetMapping("/calendar")
    public void carlendarlist(){

    }
}
