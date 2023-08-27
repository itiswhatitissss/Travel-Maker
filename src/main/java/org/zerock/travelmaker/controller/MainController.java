package org.zerock.travelmaker.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.travelmaker.domain.Party;
import org.zerock.travelmaker.domain.PartyDetail;
import org.zerock.travelmaker.domain.Plan;
import org.zerock.travelmaker.domain.Users;
import org.zerock.travelmaker.dto.FriendDTO;
import org.zerock.travelmaker.dto.PartyDTO;
import org.zerock.travelmaker.dto.PlanDTO;
import org.zerock.travelmaker.dto.UserDTO;
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
//            model.addAttribute("list",0);
        } else {
            for(int i=0;i<friendDTO.size();i++) {
                Map<String, Object> userMap = friendDTO.get(0); // 첫 번째 검색 결과 사용

                // 필드 값 추출
                Long fno = (Long) userMap.get("uno");
                String name = (String) userMap.get("name");
                String id = (String) userMap.get("id");
//            log.info("세션 : "+session);
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

        List<Map<String,Object>> partyList =mainService.getParty(uno);
        model.addAttribute("partyDTO",partyList);
        List<Map<String,Object>> planList =mainService.getPlan(pno);
        model.addAttribute("planDTO",planList);
        Long partyOne = mainService.getPartyOne(pno);
        model.addAttribute("pno",partyOne);

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

    @PostMapping("/deletePlan")
    public ResponseEntity<String> deletePlan(@RequestParam("plno") Long plno) {
        mainService.deletePlan(plno);
        return ResponseEntity.ok("success");
    }
    @PostMapping("/friendSearch")
    public String friendSearch(HttpServletRequest request, @RequestParam(name = "searchText", required = false) String searchText, RedirectAttributes rttr, HttpSession session) {

        String referer = request.getHeader("referer");
        log.info("검색text : " +searchText);

        List<Map<String, Object>> search = friendService.friendSearch(searchText);
        log.info("검색결과 : "+search);

        session.setAttribute("friendSearchResult", search);
        List<Map<String, Object>> fri = (List<Map<String, Object>>) session.getAttribute("friendSearchResult");
        log.info("세션 : "+fri);

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
    @PostMapping("/friendInsert")
    public String friendInsert(HttpServletRequest request,@RequestParam(name = "selectedFriends", required = false) List<Long> selectedFriends , Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();

        Long uno = loginService.getUno(username);

        String referer = request.getHeader("referer");

        for (int i=0; i<selectedFriends.size();i++){
            Long fno = selectedFriends.get(i);
            FriendDTO friendDTO = new FriendDTO(uno,fno);
            friendService.insertFriend(friendDTO);
        }
        return "redirect:" + referer;
    }
}
