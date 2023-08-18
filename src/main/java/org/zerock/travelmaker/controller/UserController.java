package org.zerock.travelmaker.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.travelmaker.mapper.SampleMapper;

@Controller
@Log4j2
@RequestMapping("/travelmaker")
@RequiredArgsConstructor
public class UserController {

    @GetMapping("/login") //로그인페이지
    public void login() {

    }


}
