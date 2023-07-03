package com.project.web.error;

import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class WebServerCustomizer implements WebServerFactoryCustomizer<ConfigurableWebServerFactory> {
    @Override
    public void customize(ConfigurableWebServerFactory factory) {
        //404 에러일 때, 해당 request url을 mapping한다.
        ErrorPage error404 = new ErrorPage(HttpStatus.NOT_FOUND, "/error/404");

        //500 에러일 때, 해당 request url을 mapping한다.
        ErrorPage error500 = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error/500");

        //그 외 에러일 때, 해당 request url을 mapping한다.
        ErrorPage errorEx = new ErrorPage(Exception.class, "/error/ex");

        factory.addErrorPages(error404, error500, errorEx);
    }
}