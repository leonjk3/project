package com.project.domain.signup;

import com.project.domain.user.SexType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/signUp")
@Slf4j
public class SignUpController {
    private final SignUpService signUpService;

    @ModelAttribute("sexTypes")
    public SexType[] sexTypes(){
        return SexType.values();
    }

    @GetMapping
    public String getSignUp(Model model) {
        SignUpForm signUpForm = new SignUpForm();
        model.addAttribute("signUpForm", signUpForm); //기본 객체를 하나 넣어주어 thymeleaf object기능을 사용한다.

        return "/login/signUp";
    }

    @PostMapping
    public String addSignUp(@Validated @ModelAttribute SignUpForm signUpForm, BindingResult bindingResult) {
        // Field에대한 에러가 있을시 메세지와 함께 회원가입창으로 보낸다.
        if (bindingResult.hasErrors()) {
            return "/login/signUp";
        }

        //이미 가입된 아이디 일시 메세지와 함께 회원가입창으로 보낸다.
        if (signUpService.findUser(signUpForm.getLoginId())) {
            bindingResult.addError(new FieldError("signUpForm", "loginId", "이미 존재하는 사용자 입니다."));
            return "/login/signUp";
        }

        signUpService.signUp(signUpForm);
        return "redirect:/";
    }
}