package com.project.web.error;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller
public class ErrorTestController {
    @GetMapping("/error-404")
    public void error404(HttpServletResponse response) throws IOException {
        response.sendError(404, "404 error");
    }

    @GetMapping("/error-500")
    public void error500(HttpServletResponse response) throws IOException {
        response.sendError(500, "500 error");
    }

    @GetMapping("/error-ex")
    public void errorEx(HttpServletResponse response) throws Exception {
        throw new Exception("예외발생!");
    }
}
