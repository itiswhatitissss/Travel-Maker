package org.zerock.travelmaker.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.zerock.travelmaker.domain.SchedulerDetail;
import org.zerock.travelmaker.domain.Users;
import org.zerock.travelmaker.dto.SchedulerDetailDTO;
import org.zerock.travelmaker.service.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Log4j2
@RequestMapping("/travelmaker/plan/*")
@RequiredArgsConstructor
public class PlanController {
    private final FriendService friendService;
    private final UserService userService;
    private final MainService mainService;
    private final SchedulerService schedulerService;
    private final AttendService attendService;

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

        Long attendCheck = attendService.checkAttend(uno,plno);
        if (attendCheck !=null){
            model.addAttribute("attendCheck",attendCheck);
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
    public String attendDo(@RequestParam("plno") Long plno, Authentication authentication,@RequestParam(name = "attend") Long attend){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        Long uno = userService.getUno(username);

        attendService.modifyAttend(uno,plno,attend);

        return "redirect:/travelmaker/plan/list";
    }
    @PostMapping("/modifyattend")
    public String modifyAttend(@RequestParam("plno") Long plno, Authentication authentication,@RequestParam(name = "attend") Long attend){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        Long uno = userService.getUno(username);

        attendService.modifyAttend(uno,plno,attend);

        return "redirect:/travelmaker/plan/list";
    }
}
