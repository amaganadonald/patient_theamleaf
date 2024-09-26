package com.amagana.patient_mvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {

    @GetMapping("/403")
    public String errorPage() {
        return "403";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
