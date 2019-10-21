package com.jilinwula.security.adapter;

import com.jilinwula.security.filter.CodeImageFilter;
import com.jilinwula.security.utils.SimplePasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private SuccessHandler successHandler;

    @Autowired
    private FailureHandler failureHandler;

    @Autowired
    private CodeImageFilter codeImageFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new SimplePasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf() // csrf攻击
                .disable()
                .addFilterBefore(codeImageFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin() // 表单登陆
                .loginPage("/login.html") // 登陆页面
                .loginProcessingUrl("/login/oneself") // 登陆表单提交请求
                .successHandler(successHandler) // 登陆成功处理器
                .failureHandler(failureHandler) // 登陆失败处理器
                .and()
                .authorizeRequests() // 对请求进行授权
                .antMatchers("/login.html", "/code/image") // 指定相应的请求
                .permitAll() // 不需要验证
                .anyRequest() // 任何请求
                .authenticated(); // 都需要身份认证
    }
}
