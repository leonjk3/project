package com.project.web.session;

import com.project.domain.sign.Sign;
import com.project.domain.sign.SignInForm;
import com.project.domain.user.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.hibernate.Session;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class SignInArgumentResolver implements HandlerMethodArgumentResolver {

    //해당 Resolver가 동작해야하는지 여부를 return한다.
    @Override
    public boolean supportsParameter(MethodParameter parameter) {

        //@Sign이라는 Annotation이 넘어왔는지 여부
        boolean hasSignInAnnotation = parameter.hasParameterAnnotation(Sign.class);

        //넘어온 파라미터가 User.class인지 여부
        boolean hasSignInType = User.class.isAssignableFrom(parameter.getParameterType());

        return hasSignInAnnotation && hasSignInType;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        //세션에서 로그인정보를 읽어 return해준다.
        HttpServletRequest httpRequest = (HttpServletRequest) webRequest.getNativeRequest();
        HttpSession session = httpRequest.getSession(false);

        if (session == null) {
            return null;
        }

        return session.getAttribute(SessionConst.SESSION_NAME);
    }
}
