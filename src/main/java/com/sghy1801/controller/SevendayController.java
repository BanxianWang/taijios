package com.sghy1801.controller;

import com.sghy1801.entity.Sevenday;
import com.sghy1801.service.SevendayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/jsp")
@Controller
public class SevendayController {

    @Autowired
    private SevendayService service;

    @RequestMapping("/getsevenday")
    public void getSevenDay(){
        List<Sevenday> list = service.getSevenDay();
        for (Sevenday sevenday : list) {
            System.out.println(sevenday.getDate());
        }
    }
}
