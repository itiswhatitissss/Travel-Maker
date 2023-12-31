package org.zerock.travelmaker.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.travelmaker.domain.Users;
import org.zerock.travelmaker.dto.UserDTO;
import org.zerock.travelmaker.service.MailSendService;
import org.zerock.travelmaker.service.UserService;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/travelmaker/user")
public class UserController {

    private final UserService userService;
    private final MailSendService mailSendService;

    @GetMapping("/login")
    public void loginGET(String error, String logout){
        log.info("login get........");
        log.info("logout : "+ logout);

        if(logout != null){
            log.info("user logout.......................");
        }
    }

    @GetMapping("/loginSuccess")
    public String loginSuccess(Model model, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();

        Long uno = userService.getUno(username);
        log.info("uno-------------------------------->" + uno);
        model.addAttribute("uno", uno);

        return "redirect:/travelmaker/main/list?uno=" + uno;
    }

//    @GetMapping("/signup")
//    public void signupGET() {};

    @PostMapping("/signup")
    public String signupPOST(UserDTO userDTO, RedirectAttributes rttr) {

        log.info("signup post...");
        log.info(userDTO);

        try {
            userService.join(userDTO);
        } catch (UserService.IdExistException e) {

            rttr.addFlashAttribute("error", "id");
            return "redirect:/travelmaker/user/signup";
        }

        rttr.addFlashAttribute("result", "success");

        return "redirect:/travelmaker/user/login"; //회원 가입 후 로그인페이지
    };

    @GetMapping("/modify")
    public void modifyGET() {};

    @PostMapping("/modify")
    public void modifyPOST(Model model, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        Long uno = userService.getUno(username);

        List<Map<String, Object>> usersDTO = userService.userList(uno);
        model.addAttribute("usersDTO", usersDTO);
    };
    @PostMapping("/mailcheck")
    public ResponseEntity<String> mailCheck(@RequestBody HashMap<String, Object> user){

        String username = (String) user.get("username");
        String authNum = mailSendService.joinEmail(username);

        return ResponseEntity.status(HttpStatus.OK).body(authNum);
    }

}
