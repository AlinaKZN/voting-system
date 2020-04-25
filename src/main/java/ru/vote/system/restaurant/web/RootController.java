package ru.vote.system.restaurant.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//стартовая страница http://localhost:8050/rest
@RestController
public class RootController {

    public static final String V_1 = "/v1";

    @GetMapping("/")
    @ResponseBody
    public String welcome() {
        return "Welcome to RestTemplate Example.";
    }
}

