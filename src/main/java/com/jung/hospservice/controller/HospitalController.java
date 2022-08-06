package com.jung.hospservice.controller;

import com.jung.hospservice.dto.HospitalDto;
import com.jung.hospservice.repository.HospitalRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@Controller
public class HospitalController {

    private final HospitalRepository hospitalRepository;

    @GetMapping("/home")
    public String home() {

        return "index";
    }

    @PostMapping("/home")
    public String showHospital(String sidoCdNm, String sgguCdNm, Model model) {

        List<HospitalDto> hospitals = hospitalRepository.mFindHospital(sidoCdNm, sgguCdNm);

        model.addAttribute("hospitals", hospitals);

        return "showHospital";
    }


}


