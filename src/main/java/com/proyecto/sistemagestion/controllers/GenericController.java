package com.proyecto.sistemagestion.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GenericController {

    @GetMapping({"/login"})
    public String login(){
        return "login";
    }
    @GetMapping("/")
    public String MainPage(){
        return "mainPage";
    }

    @RequestMapping("/denied")
    public String accessDenied(){
        return "deniedPage";
    }

}