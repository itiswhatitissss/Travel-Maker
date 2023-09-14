package org.zerock.travelmaker.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.http.HttpStatus;
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
import org.zerock.travelmaker.repository.PlanRepository;
import org.zerock.travelmaker.service.FriendService;
import org.zerock.travelmaker.service.PlanService;
import org.zerock.travelmaker.service.UserService;
import org.zerock.travelmaker.service.MainService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@Log4j2
@RequestMapping("/travelmaker/main/*")
@RequiredArgsConstructor
public class MainController {

    private final MainService mainService;
    private final UserService userService;
    private final FriendService friendService;
    private final PlanService planService;
    private final PlanRepository planRepository;

    @GetMapping("/list")
    public void mainList(Model model, Long uno, Long pno, Authentication authentication){
//        List<Map<String, Object>> friendDTO = (List<Map<String, Object>>) session.getAttribute("friendSearchResult");
//        if (friendDTO == null) {
//            List<Map<String,Object>> friend =friendService.friendList(uno);
//        } else {
//            for(int i=0;i<friendDTO.size();i++) {
//                Map<String, Object> userMap = friendDTO.get(0); // 첫 번째 검색 결과 사용
//
//                // 필드 값 추출
//                Long fno = (Long) userMap.get("uno");
//                String name = (String) userMap.get("name");
//                String id = (String) userMap.get("id");
////            log.info("세션 : "+session);
//            Users users = Users.builder()
//                    .id(id)
//                    .uno(fno)
//                    .name(name)
//                    .build();
//                model.addAttribute("friendSearchResult", users);
////            model.addAttribute("list",1);
//            }
//            List<Map<String, Object>> friend = friendService.friendList(uno);
//            model.addAttribute("friendDTO", friend);
//            session.removeAttribute("friendSearchResult"); // 세션에서 검색 결과 제거
//        }

        List<Map<String,Object>> partyList =mainService.getParty(uno);
        model.addAttribute("partyDTO",partyList);
        List<Map<String,Object>> planList =mainService.getPlan(pno);
        model.addAttribute("planDTO",planList);
        Long partyOne = mainService.getPartyOne(pno);
        model.addAttribute("pno",partyOne);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        Long uno2 = userService.getUno(username);
        model.addAttribute("uno", uno2);
        model.addAttribute("username", username);

        List<Map<String, Object>> usersDTO = userService.userList(uno);
        model.addAttribute("usersDTO", usersDTO);
    }

    @PostMapping("/partyPopup")
    public String partyPopupPost(Long uno, Long pno, @RequestParam(name = "selectedFriends", required = false) List<Long> selectedFriends,
                               @RequestParam("title") String title){
        if (selectedFriends == null) {
            selectedFriends = new ArrayList<Long>(); // 빈 리스트로 초기화
        }

        PartyDTO partyDTO = PartyDTO.builder()
                .partyName(title)
                .build();
        selectedFriends.add(uno);
        mainService.PartyRegister(partyDTO,selectedFriends);

        return "redirect:list?&uno="+uno; //pno값 없을 때 400에러 버그 수정
    }

    @PostMapping("/planPopup")
    public String planPopupPost(Plan plan, Long pno, Long uno, Model model, MultipartFile file)throws Exception{
        planService.write(plan,file,pno);
        model.addAttribute("message","글작성이 완료되었습니다");
        return "redirect:list?pno="+pno+"&uno="+uno;
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

        Long uno = userService.getUno(username);

        String referer = request.getHeader("referer");

        for (int i=0; i<selectedFriends.size();i++){
            Long fno = selectedFriends.get(i);
            friendService.deleteFriend(uno,fno);
        }
        return "redirect:" + referer;
    }

    @PostMapping("/deleteParty")
    public String deleteParty(Long pno1,HttpServletRequest request,@RequestParam(name = "selectedParties", required = false) List<Long> selectedParties , Authentication authentication){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        Long uno = userService.getUno(username);

        for (int i=0; i<selectedParties.size();i++){
            Long pno = selectedParties.get(i);
            mainService.deleteParty(uno,pno);
        }
        return "redirect:list?pno="+pno1+"&uno="+uno;
    }

    @PostMapping("/friendInsert")
    public String friendInsert(HttpServletRequest request,@RequestParam(name = "selectedFriends", required = false) List<Long> selectedFriends , Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();

        Long uno = userService.getUno(username);

        String referer = request.getHeader("referer");

        for (int i=0; i<selectedFriends.size();i++){
            Long fno = selectedFriends.get(i);
            FriendDTO friendDTO = new FriendDTO(uno,fno);
            friendService.insertFriend(friendDTO);
        }
        return "redirect:" + referer;
    }

    @PostMapping("/autocomplete")
    @ResponseBody
    public Map<String, Object> autocomplete(@RequestParam String keyword, @RequestParam Long uno) {
        List<String> resultList = mainService.searchPartyByName(keyword, uno);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultList", resultList);

        return resultMap;
    }

    @GetMapping("/getPlan")
    public ResponseEntity<PlanDTO> getPlan(@RequestParam("plno") Long plno) {
        try {
            PlanDTO plan = mainService.readOne(plno); // plno에 해당하는 플랜 정보 가져오기
            log.info("planDTO======================>"+plan);
            if (plan != null) {
                return ResponseEntity.ok(plan);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/modifyPlan")
    @ResponseBody
    public String modifyPlan(@RequestParam("plno") Long plno,
                             @RequestParam("title") String title,
                             @RequestParam("start") String start,
                             @RequestParam("end") String end,
                             Model model) {

        planService.planmodify(title,start,end,plno);
        return "success";

        // 예시: 수정 작업 실패 시
        // return "failure";
    }

    @GetMapping("/searchParty")
    public String searchPartyOne(@RequestParam("partyName")String partyname, @RequestParam("uno")Long uno){
        Long pno = mainService.searchPartyOne(partyname);
        return "redirect:list?pno="+pno+"&uno="+uno;
    }

    @PostMapping("/partyModify")
    public ResponseEntity<String> partyModify(@RequestParam("pno") Long pno,
                                              @RequestParam(name = "fnoList") String fnoListString,
                                              @RequestParam(name = "title") String title){

        mainService.modifyParty(title,pno);

        if(fnoListString.isEmpty()) {
        }else {
            List<Long> selectedFriends = Arrays.stream(fnoListString.split(","))
                    .map(Long::parseLong)
                    .collect(Collectors.toList());

            for (int i = 0; i < selectedFriends.size(); i++) {
                Long fno = selectedFriends.get(i);
                mainService.userPartySave(pno,fno);
            }
        }
        String result = "success"; // 성공 시 "success", 실패 시 다른 값을 설정

        return ResponseEntity.ok(result);
    }
    @GetMapping("/getPartyModify")
    public ResponseEntity<List<Map<String, Object>>> patryModifyView(@RequestParam("pno") Long pno, @RequestParam("uno") Long uno){
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
                }
            }
        }
        partylist.addAll(friendlist);

        log.info("partylist.get(1).get('member')  : "+partylist.get(0).get("member"));

        return ResponseEntity.ok(partylist);
    }
    @GetMapping("/getFriend")
    public ResponseEntity<List<Map<String, Object>>> getFriend(Authentication authentication){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        Long uno = userService.getUno(username);

        List<Map<String,Object>> friendList =friendService.friendList(uno);

        return ResponseEntity.ok(friendList);
    }

}
