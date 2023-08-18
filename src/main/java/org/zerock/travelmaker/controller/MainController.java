package org.zerock.travelmaker.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.travelmaker.service.MainService;

import java.util.List;
import java.util.Map;

@Controller
@Log4j2
@RequestMapping("/travelmaker/main/*")
@RequiredArgsConstructor
public class MainController {

    private final MainService mainService;

    @GetMapping("/list")
    public void mainList(Long uno){
        List<Map<String,Object>> partyList = mainService.getParty(uno);
    }

    @GetMapping("/galleryList")
    public void gallery(){

    }
}
