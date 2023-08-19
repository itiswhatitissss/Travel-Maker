package org.zerock.travelmaker.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.travelmaker.dto.UserDTO;
import org.zerock.travelmaker.mapper.SampleMapper;
import org.zerock.travelmaker.service.LoginService;

import javax.servlet.http.HttpSession;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/travelmaker")
public class UserController {

    private final LoginService loginService;

    @GetMapping("/login")
    public void login(){
    }

    @PostMapping("/login") //로그인페이지
    public String login( @RequestParam String id, @RequestParam String password, RedirectAttributes rttr) {

        log.info("id : "+id);
        log.info("pw : "+password);

        Long uno = loginService.getUno(id, password);

        log.info("uno in controller " + uno);
//        session.setAttribute("uno", uno);

        rttr.addAttribute("uno",uno);

        return "redirect:/travelmaker/main/list";

    }
}
