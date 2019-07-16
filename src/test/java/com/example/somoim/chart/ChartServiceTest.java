package com.example.somoim.chart;

import com.example.somoim.service.ChartService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ChartServiceTest {
    @Autowired
    ChartService chartService;

    @Test
    public void getMonthlyAttendRank_SUCCESS(){
        chartService.getMonthlyAttendRank();
    }

    @Test
    public void getMonthlyAttendRate_SUCCESS(){
        chartService.getMonthlyAttendRate();
    }
}
