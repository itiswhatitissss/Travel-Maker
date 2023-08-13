package org.zerock.travelmaker.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.travelmaker.mapper.SampleMapper;

@Controller
@Log4j2
@RequestMapping("/travelmaker")
@RequiredArgsConstructor
public class SampleController {
    @Autowired
    private SampleMapper sampleMapper;

    @GetMapping("/loginSuccess") //로그인이 성공했을때 넘어가는 페이지
    public void loginSuccess() {
    }

    @GetMapping("/scheduler")
    public void scheduler() {
    }


}


