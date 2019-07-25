package com.ultra.web;

import org.springframework.stereotype.Controller;

@Controller
public class IndexController {

    public String index() {
        return "/swagger-ui.html";
    }
}
