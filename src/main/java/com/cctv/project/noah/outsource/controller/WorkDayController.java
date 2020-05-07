package com.cctv.project.noah.outsource.controller;


import com.cctv.project.noah.outsource.entity.EventVO;
import com.cctv.project.noah.outsource.entity.WorkDay;
import com.cctv.project.noah.outsource.service.Result;
import com.cctv.project.noah.outsource.service.WorkDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/outsource/workDay")
public class WorkDayController {


    private String prefix = "outsource/workDay";

    @Autowired
    WorkDayService workDayService;
    @GetMapping()
    public String page() {
        return prefix + "/calendar";
    }

    @RequestMapping("/init")
    @ResponseBody
    public List<EventVO> init (String start, String end){
        List<WorkDay> workDays = workDayService.findHolidays(start, end);
        List<EventVO> events = new ArrayList<>();
        if (!CollectionUtils.isEmpty(workDays)) {
            workDays.forEach(workDay -> {
                EventVO event = new EventVO(workDay.getDateString());
                events.add(event);
            });
        }
        return events;
    }

    @PostMapping(value = "/add")
    @ResponseBody
    public Result add(String date) {
        return workDayService.insert(date);

    }

    @PostMapping(value = "/delete")
    @ResponseBody
    public Result delete(String date) {
        return workDayService.delete(date);
    }
}
