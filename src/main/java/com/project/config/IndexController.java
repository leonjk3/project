package com.project.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index(){
        return "/index";
    }

    @GetMapping("/elements")
    public String element(){
        return "/elements";
    }

    @GetMapping("/generic")
    public String generic(){
        return "/generic";
    }

}
