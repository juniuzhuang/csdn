package com.duxiutianlang.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetController {

    @RequestMapping("/")
    public String sayHi(){
        return "独秀天狼springboot从入门到牛皮，少壮不努力老大徒伤悲！";
    }
}
