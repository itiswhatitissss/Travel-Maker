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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.travelmaker.domain.Party;
import org.zerock.travelmaker.domain.PartyDetail;
import org.zerock.travelmaker.domain.Plan;
import org.zerock.travelmaker.dto.PlanDTO;
import org.zerock.travelmaker.service.FriendService;
import org.zerock.travelmaker.service.PlanService;
import org.zerock.travelmaker.service.UserService;
import org.zerock.travelmaker.service.MainService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
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
    private final PlanService planService;



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

    @GetMapping("/test")
    public void plan(){}


    @GetMapping("/planPopup")
    public void planPopupGet(@RequestParam("pno") Long pno,Model model){
        model.addAttribute("pno",pno);
    }

//    @PostMapping("/planPopup")
//    public void planPopupPost(PlanDTO planDTO, Long pno){
//        log.info("planDTO=======> " + planDTO);
//        log.info("pno=====+++++++>"+pno);
//        mainService.planRegister(planDTO,pno);
//    }

    @PostMapping("/planPopup")
    public void planPopup(Plan plan, Long pno, Model model, MultipartFile file)throws Exception{
        planService.write(plan,file,pno);

        //model.addAttribute("message","글작성이 완료되었습니다");
        //model.addAttribute("searchUrl","/travelmaker/main/list");

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
@PostMapping("/friendSearch")
public String friendSearch(HttpServletRequest request, @RequestParam(name = "searchText", required = false) String searchText, RedirectAttributes rttr) {

    String referer = request.getHeader("referer");
    log.info("검색text : " +searchText);

    List<Map<String, Object>> search =friendService.friendSearch(searchText);
    rttr.addAttribute("friendDTO",search);

    return "redirect:" + referer;
}
    @PostMapping("/friendDelete")
    public String friendDelete(HttpServletRequest request,@RequestParam(name = "selectedFriends", required = false) List<Long> selectedFriends , Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();

        Long uno = loginService.getUno(username);

        String referer = request.getHeader("referer");

        for (int i=0; i<selectedFriends.size();i++){
            Long fno = selectedFriends.get(i);
            friendService.deleteFriend(uno,fno);
        }
        return "redirect:" + referer;
    }
}
