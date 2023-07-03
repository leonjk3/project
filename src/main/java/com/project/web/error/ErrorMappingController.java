package com.project.web.error;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class ErrorMappingController {

    @RequestMapping("/error/404")
    public String error404(HttpServletRequest request, HttpServletResponse response) {
        return "/error/404";
    }

    @RequestMapping("/error/500")
    public String error500(HttpServletRequest request, HttpServletResponse response) {
        return "/error/500";
    }

    @RequestMapping("/error/ex")
    public String errorEx(HttpServletRequest request, HttpServletResponse response) {
        return "/error/ex";
    }
}
