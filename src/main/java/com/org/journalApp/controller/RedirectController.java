package com.org.journalApp.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RedirectController {

    @GetMapping("/")
    public String redirectToHealthCheck() {
        return "redirect:/public/health-check";
    }
}
