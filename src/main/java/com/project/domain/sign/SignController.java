package com.project.domain.sign;

import com.project.domain.session.SessionConst;
import com.project.domain.user.SexType;
import com.project.domain.user.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
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

@Controller
@RequiredArgsConstructor
@RequestMapping("/sign")
@Slf4j
public class SignController {
    private final SignService signService;

    @ModelAttribute("sexTypes")
    public SexType[] sexTypes(){
        return SexType.values();
    }

    //회원가입 start
    @GetMapping("/signUp")
    public String getSignUp(@ModelAttribute SignUpForm signUpForm) { //기본 객체를 하나 넣어주어 thymeleaf object기능을 사용한다.
        return "/login/signUp";
    }

    @PostMapping("/signUp")
    public String addSignUp(@Validated @ModelAttribute SignUpForm signUpForm, BindingResult bindingResult, HttpServletRequest request) {
        // Field에대한 에러가 있을시 메세지와 함께 회원가입창으로 보낸다.
        if (bindingResult.hasErrors()) {
            return "/login/signUp";
        }

        //비밀번호 확인
        if(!signUpForm.getPassword().equals(signUpForm.getPasswordCheck())){
            bindingResult.addError(new FieldError("signUpForm", "passwordCheck", "비밀번호 확인이 일치하지 않습니다."));
            return "/login/signUp";
        }

        //이미 가입된 아이디 일시 메세지와 함께 회원가입창으로 보낸다.
        User user = signService.findUser(signUpForm.getLoginId());
        if (user != null) {
            bindingResult.addError(new FieldError("signUpForm", "loginId", "이미 존재하는 사용자 입니다."));
            return "/login/signUp";
        }

        signService.signUp(signUpForm);
        User addUser = signService.findUser(signUpForm.getLoginId());
        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.SESSION_NAME, addUser);

        return "redirect:/";
    }
    //회원가입 end

    //로그인 start
    @GetMapping("/signIn")
    public String getSignIn(@ModelAttribute SignInForm signInForm){
        return "/login/signIn";
    }

    @PostMapping("/signIn")
    public String addSignIn(@Validated @ModelAttribute SignInForm signInForm, BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return "/login/signIn";
        }

        User user = signService.findLoginUser(signInForm.getLoginId(), signInForm.getPassword());
        if (user == null) {
            bindingResult.addError(new ObjectError("signInForm", "아이디나 비밀번호가 틀렸습니다."));
            return "/login/signIn";
        }else{
            //로그인 성공시 세션처리
            HttpSession httpSession = request.getSession();
            httpSession.setAttribute(SessionConst.SESSION_NAME, user);
        }
        return "redirect:/";
    }
    //로그인 end
}