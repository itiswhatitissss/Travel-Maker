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
import org.zerock.travelmaker.domain.Users;
import org.zerock.travelmaker.service.FriendService;
import org.zerock.travelmaker.service.MainService;
import org.zerock.travelmaker.service.SchedulerService;
import org.zerock.travelmaker.service.UserService;

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
//    private final SchedulerService schedulerService;

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
//            model.addAttribute("list",1);
            }
            List<Map<String, Object>> friend = friendService.friendList(uno);
            model.addAttribute("friendDTO", friend);
            session.removeAttribute("friendSearchResult"); // 세션에서 검색 결과 제거

        }
    };

//    @PostMapping("/scheduler")
//    public String processDateForm(@RequestParam Date startDate, @RequestParam Date endDate, Model model) {
//        List<Date> datesBetween = schedulerService.getDatesBetween(startDate, endDate);
//
//        model.addAttribute("startTime", startDate);
//        model.addAttribute("endTime", endDate);
//        model.addAttribute("datesBetween", datesBetween);
//
//        return "redirect:/travelmaker/plan/planDetail";
//    }



}
