package com.jung.hospservice.controller;

import com.jung.hospservice.repository.HospitalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class HospitalController {

    private HospitalRepository hospitalRepository;

    @GetMapping("/home")
    public String home() {
        return "index";
    }

}


