package org.zerock.travelmaker.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.zerock.travelmaker.service.LoginService;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/travelmaker")
public class UserController {

    private final LoginService loginService;

//    @PreAuthorize("permitAll()")
    @GetMapping("/login")
    public void loginGET(String error, String logout){
        log.info("login get........");
        log.info("logout : "+logout);

        if(logout != null){
            log.info("user logout........");
        }
    }

//    @PostAuthorize("authenticatied('USER')")
    @PostMapping("/login") //로그인페이지
    public String login( @RequestParam String username, @RequestParam String password) {

        log.info("username : "+username);
        log.info("pw : "+password);

        Long uno = loginService.getUno(username, password);

        return "redirect:/travelmaker/main/list";

    }
}
