package com.example.somoim.controller;

import com.example.somoim.dto.ChartMonthlyAttendRankDto;
import com.example.somoim.error.ServiceException;
import com.example.somoim.service.ChartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Slf4j
@Controller
public class ChartController {

    @Autowired
    private ChartService chartService;

    @GetMapping("/chart")
    public String getChart(@RequestParam String pageName, ModelMap model){
        //MonthlyAttendRank select
        List<ChartMonthlyAttendRankDto> attendRank =  chartService.getMonthlyAttendRank();
        List<Map<String,Integer>>      attendRate =  chartService.getMonthlyAttendRate();

        model.addAttribute("pageName",pageName);
        model.addAttribute("attendRank",attendRank);
        model.addAttribute("attendRate",attendRate);

        log.info("attendRank{}",attendRank.toString());
        log.info("attendRate{}",attendRate.toString());
        return "/charts";
    }

    public void getMonthlyAttendRank () throws ServiceException {
        chartService.getMonthlyAttendRank();

    }
}
