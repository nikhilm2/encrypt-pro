package com.example.blowalgo.comtroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AboutController {
     @GetMapping("/about")
    public String aboutPage() {
        System.out.print("Hello");
        return "about"; 
    }
}
