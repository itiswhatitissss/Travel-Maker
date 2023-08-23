package org.zerock.travelmaker.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.zerock.travelmaker.dto.PlanDTO;
import org.zerock.travelmaker.service.FriendService;
import org.zerock.travelmaker.service.UserService;
import org.zerock.travelmaker.service.MainService;

import java.util.List;
import java.util.Map;

@Controller
@Log4j2
@RequestMapping("/travelmaker/main/*")
@RequiredArgsConstructor
public class MainController {

    private final MainService mainService;
    private final UserService loginService;
    private final FriendService friendService;



    @PreAuthorize("hasRole('USER')")
    @GetMapping("/list")
    public void mainList(Model model, Long uno, Long pno, Authentication authentication){
        List<Map<String,Object>> party =mainService.getParty(uno);
        model.addAttribute("partyDTO",party);
        List<Map<String,Object>> plan =mainService.getPlan(pno);
        model.addAttribute("planDTO",plan);
        List<Map<String,Object>> friend =friendService.friendList(uno);
        model.addAttribute("friendDTO",friend);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        Long uno2 = loginService.getUno(username);
        model.addAttribute("uno", uno2);
    }


//    @GetMapping("/list")
//    public void plan(Long pno, Model model){
//        List<Map<String,Object>> result = mainService.getPlan(pno);
//        model.addAttribute("planDTO",result);
//    }

//    @GetMapping("/plan")
//    public void plan(){}

    @GetMapping("/planPopup")
    public void planPopupGet(){
    }

    @PostMapping("/planPopup")
    public void planPopupPost(PlanDTO planDTO, @RequestParam("pno") Long pno){
        log.info("planDTO=======> " + planDTO);
        log.info("pno=====+++++++>"+pno);
        Long plno = mainService.planRegister(planDTO);
//        PartyDetail partyDetail = PartyDetail.builder().pnoByPartyDetail(pno).plnoByPartyDetail(plno).build();
    }

//    @PostMapping("/planPopup")
//    public String planPopupPost(PlanDTO planDTO, RedirectAttributes rttr, HttpSession session) {
//        log.info("planDTO=======> " + planDTO);
//        long plno = mainService.planRegister(planDTO);
//
//        // 데이터를 세션에 저장
//        session.setAttribute("planTitle", planDTO.getTitle());
//        session.setAttribute("planStart", planDTO.getStart());
//        session.setAttribute("planEnd", planDTO.getEnd());
//
//        return "redirect:/travelmaker/main/plan";
//    }

}
