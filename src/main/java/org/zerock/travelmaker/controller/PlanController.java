package org.zerock.travelmaker.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Log4j2
@RequestMapping("/travelmaker/plan/*")
@RequiredArgsConstructor
public class PlanController {

    @GetMapping("/planDetail")
    public void planDetailGET(@RequestParam("plno") Long plno, Model model) {
        log.info("plno ==================================> : " + plno);
    };
}
