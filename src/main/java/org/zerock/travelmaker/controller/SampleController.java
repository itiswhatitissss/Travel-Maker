package org.zerock.travelmaker.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.travelmaker.domain.SampleDomain;
import org.zerock.travelmaker.dto.SampleDTO;
import org.zerock.travelmaker.mapper.SampleMapper;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
@Log4j2
@RequestMapping("/sample")
@RequiredArgsConstructor
public class SampleController {
    @Autowired
    private SampleMapper sampleMapper;

    @GetMapping("/main")
    public void main() {
    }

    @GetMapping("/login")
    public void login() {}

    @GetMapping("/boardWriteForm")
    public void boardWriteForm() {}

    @GetMapping("/friendSearchForm")
    public void friendSearchForm() {}
}


