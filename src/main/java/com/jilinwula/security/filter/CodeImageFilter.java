package com.jilinwula.security.filter;

import cn.hutool.captcha.ShearCaptcha;
import com.jilinwula.security.exception.CodeImageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CodeImageFilter extends OncePerRequestFilter {

    @Autowired
    private ShearCaptcha shearCaptcha;

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException, AuthenticationException {
        if (!request.getRequestURI().equals("/security/login/oneself")) {
            filterChain.doFilter(request, response);
            return;
        }
        String codeImage = request.getParameter("codeImage");
        if (StringUtils.isEmpty(codeImage)) {
            authenticationFailureHandler.onAuthenticationFailure(request,response,new CodeImageException("验证码为空"));
        }
        if (!shearCaptcha.verify(codeImage)) {
            authenticationFailureHandler.onAuthenticationFailure(request,response,new CodeImageException("验证码不正确"));
        }
        filterChain.doFilter(request, response);
    }
}
