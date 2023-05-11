package com.project.web;

import com.project.domain.sign.Sign;
import com.project.domain.sign.SignInForm;
import com.project.domain.user.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index(@Sign User user, Model model) {
        //세션이 없다면 그냥 Home화면으로 이동
        if (user == null) {
            return "/index";
        }

        //세션이 있다면 model에 담아 로그인사용자 전용 Home화면으로 이동
        model.addAttribute("user", user);
        return "/login";
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
