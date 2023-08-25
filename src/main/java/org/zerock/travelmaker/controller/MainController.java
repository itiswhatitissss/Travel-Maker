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
import org.zerock.travelmaker.dto.PartyDTO;
import org.zerock.travelmaker.dto.PlanDTO;
import org.zerock.travelmaker.service.FriendService;
import org.zerock.travelmaker.service.PlanService;
import org.zerock.travelmaker.service.UserService;
import org.zerock.travelmaker.service.MainService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.util.ArrayList;
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
    public void mainList(Model model, Long uno, Long pno, Authentication authentication, HttpSession session){
        List<Map<String, Object>> friendDTO = (List<Map<String, Object>>) session.getAttribute("friendSearchResult");
        if (friendDTO == null) {
            List<Map<String,Object>> friend =friendService.friendList(uno);
            model.addAttribute("friendDTO",friend);
            model.addAttribute("list",0);
        } else {
            model.addAttribute("friendDTO",friendDTO);
            model.addAttribute("list",1);
            session.removeAttribute("friendSearchResult"); // 세션에서 검색 결과 제거
        }

        List<Map<String,Object>> party =mainService.getParty(uno);
        model.addAttribute("partyDTO",party);
        List<Map<String,Object>> plan =mainService.getPlan(pno);
        model.addAttribute("planDTO",plan);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        Long uno2 = loginService.getUno(username);
        model.addAttribute("uno", uno2);
    }

    @GetMapping("/partyPopup")
    public void partyPopupGet(@RequestParam("uno")Long uno,Model model){
        model.addAttribute("uno",uno);
        List<Map<String,Object>> friend =friendService.friendList(uno);
        model.addAttribute("friendDTO",friend);
    }

    @PostMapping("/partyPopup")
    public void partyPopupPost(@RequestParam("uno")Long uno,@RequestParam(name = "selectedFriends", required = false) List<Long> selectedFriends,
                               @RequestParam("title") String title){
        if (selectedFriends == null) {
            selectedFriends = new ArrayList<Long>(); // 빈 리스트로 초기화
        }

        PartyDTO partyDTO = PartyDTO.builder()
                .partyName(title)
                .build();
        selectedFriends.add(uno);
        mainService.PartyRegister(partyDTO,selectedFriends);
    }

    @GetMapping("/planPopup")
    public void planPopupGet(@RequestParam("pno") Long pno,Model model){
        model.addAttribute("pno",pno);
    }


    @PostMapping("/planPopup")
    public void planPopupPost(Plan plan, Long pno, Model model, MultipartFile file)throws Exception{
        planService.write(plan,file,pno);

        //model.addAttribute("message","글작성이 완료되었습니다");
        //model.addAttribute("searchUrl","/travelmaker/main/list");

    }

@PostMapping("/friendSearch")
public String friendSearch(HttpServletRequest request, @RequestParam(name = "searchText", required = false) String searchText, RedirectAttributes rttr, HttpSession session) {

    String referer = request.getHeader("referer");
    log.info("검색text : " +searchText);

//    List<Map<String, Object>> search =friendService.friendSearch(searchText);
//    rttr.addAttribute("friendDTO",search);
//
//    return "redirect:" + referer;

    List<Map<String, Object>> search = friendService.friendSearch(searchText);
    session.setAttribute("friendSearchResult", search);

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
