package com.project.config;

import com.project.web.session.SessionFilter;
import com.project.web.session.SessionInterceptor;
import com.project.web.session.SignInArgumentResolver;
import jakarta.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new SignInArgumentResolver());
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new SessionInterceptor())
                .order(0) //interceptor의 순서를 정한다.
                .addPathPatterns("/**") // [/**]는 전부 Interceptor의 범위에 있다는 뜻이다.
                .excludePathPatterns("/", "/sign/**", "/assets/**", "/images/**"); //다음과 같은 url은 interceptor에서 제외한다.
    }

//    @Bean
    public FilterRegistrationBean sessionFilter() {
        FilterRegistrationBean<Filter> sessionFilterRegistrationBean = new FilterRegistrationBean<Filter>();
        sessionFilterRegistrationBean.setFilter(new SessionFilter()); //SessionFilter 객체를 등록한다.
        sessionFilterRegistrationBean.setOrder(0); //Filter의 순서를 정한다. 순서가 작을수록 chain에 먼저 걸림
        sessionFilterRegistrationBean.addUrlPatterns("/*"); //해당 필터가 적용될 URL 패턴을 지정한다.

        return sessionFilterRegistrationBean;
    }
}
