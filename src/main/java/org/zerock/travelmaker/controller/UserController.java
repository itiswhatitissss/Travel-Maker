package org.zerock.travelmaker.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.zerock.travelmaker.domain.Users;
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

    @GetMapping("/loginSuccess")
    public String loginSuccess(Model model, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();

        Long uno = loginService.getUno(username);
        log.info("uno-------------------------------->" + uno);
        model.addAttribute("uno", uno);

        return "redirect:/travelmaker/main/list?uno=" + uno;
    }
}
