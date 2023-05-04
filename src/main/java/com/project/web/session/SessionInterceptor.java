package com.project.web.session;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

public class SessionInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //요청이 들어온 URI
        String requestURI = request.getRequestURI();

        //사용자 정보가 저장된 Session
        HttpSession session = request.getSession();

        //세션이 없거나, 세션에 로그인정보가 없다면
        if (session == null || session.getAttribute(SessionConst.SESSION_NAME) == null) {
            response.sendRedirect("/sign/signIn?redirectURL=" + requestURI);
            return false; //return타입이 boolean이기때문에 false를 해줘야 다음 process로 넘어가지 않는다.
        }
        return true;
    }
}
