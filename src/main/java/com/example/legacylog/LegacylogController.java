package com.example.legacylog;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class LegacylogController {
    
    @GetMapping("/")
    public String welcome(Model model) {
        String message = "Welcome to Legacy Log";
        log.info("logging {}", message);
        model.addAttribute("message", message);
        return "index";
    }

    @GetMapping("/get")
    public String getUser(@RequestParam(name="userId") Long userId, Model model) {
        log.info("logging: GET User Call with ID{}", userId);        
        model.addAttribute("message", createUser());
        return "result";
    }

    @PostMapping("/post")
    public String postUser(@RequestParam(name="userId") Long userId, Model model) {
        log.info("logging: POST User Call with ID{}", userId);        
        model.addAttribute("message", createUser());
        return "result";
    }

    @PostMapping("/body")
    @ResponseBody
    public String postUserBody(@RequestBody User user, Model model) {
        log.info("logging: BODY User Call with ID{}", user.id());        
        model.addAttribute("message", createUser());
        return "result";
    }

    private User createUser() {
        return new User(999L, "John Doe", "john-doe@nowhere.com", "123 North Main St.");
    }
}
