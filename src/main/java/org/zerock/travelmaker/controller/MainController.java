package org.zerock.travelmaker.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
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

    @GetMapping("/list")
    public void mainList(HttpSession session, Model model){
//        Long uno = (Long) session.getAttribute("uno" );
//        Long uno =
//        List<Map<String,Object>> partyList = mainService.getParty(uno);
//        model.addAttribute("party",partyList);
    }

    @GetMapping("/galleryList")
    public void gallery(){

    }

    @GetMapping("/plan") //http://localhost:8383/travelmaker/main/plan?plno=1 으로 치면 나오긴함
    public void plan(Long plno, Model model){
        PlanDTO planDTO = mainService.readOne(plno);
        model.addAttribute("planDTO", planDTO);
    }

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
