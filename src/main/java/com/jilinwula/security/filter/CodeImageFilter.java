package com.jilinwula.security.filter;

import com.jilinwula.security.exception.VodeImageException;
import org.springframework.security.core.AuthenticationException;
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

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException, AuthenticationException {
        if (!request.getRequestURI().equals("/security/login/oneself")) {
            filterChain.doFilter(request, response);
            return;
        }
        String codeImage = request.getParameter("codeImage");
        if (StringUtils.isEmpty(codeImage)) {
            throw new VodeImageException("验证码为空");
        }
        filterChain.doFilter(request, response);
    }
}