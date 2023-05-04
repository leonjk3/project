package com.project.web.session;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;

public class SessionFilter implements Filter {

    //SessionFilter에서 예외되는 Path들이다.
    private static final String[] excludePath = {"/", "/sign/*", "/assets/*", "/images/*"};
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String requestURI = httpRequest.getRequestURI(); //요청한 URI, SessionFilter이후에 redirect시키기위해 변수선언.

        try {
            //SessionFilter 예외처리 되지 않은 Path라면
            if (!isExclude(requestURI)) {
                //세션을 불러온다.
                HttpSession session = httpRequest.getSession();
                //세션이 null이거나, 세션에 로그인정보가 없다면 로그인화면으로 redirect시킨다.
                if (session == null || session.getAttribute(SessionConst.SESSION_NAME) == null) {
                    httpResponse.sendRedirect("/sign/signIn?redirectURL=" + requestURI);
                    return;
                }
            }
            //다음 필터를 실행하거나, Servlet, Controller를 호출한다.
            chain.doFilter(request, response);
        } catch (Exception e) {
            throw e;
        }
    }

    protected boolean isExclude(String requestURI) {
        return PatternMatchUtils.simpleMatch(excludePath, requestURI);
    }
}
