package org.zerock.travelmaker.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.travelmaker.dto.PlanDTO;
import org.zerock.travelmaker.service.MainService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@Log4j2
@RequestMapping("/travelmaker/main/*")
@RequiredArgsConstructor
public class MainController {

    private final MainService mainService;

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/list")
    public void mainList(HttpSession session, Model model, Long uno, Long pno){
        List<Map<String,Object>> party =mainService.getParty(uno);
        model.addAttribute("partyDTO",party);
        List<Map<String,Object>> plan =mainService.getPlan(pno);
        model.addAttribute("planDTO",plan);
//        Long uno = (Long) session.getAttribute("uno" );
//        List<Map<String,Object>> partyList = mainService.getParty(uno);
//        model.addAttribute("party",partyList);
    }

    @PostMapping("/createPlan")
    public void createPlan(PlanDTO planDTO){

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
    public String planPopupPost(PlanDTO planDTO, RedirectAttributes rttr){
        log.info("planDTO=======> " + planDTO);
        long plno = mainService.planRegister(planDTO);

        rttr.addFlashAttribute("plno", plno);

        return "redirect:/travelmaker/main/plan";
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
