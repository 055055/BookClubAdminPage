package com.example.somoim.config;

import com.example.somoim.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.access.expression.SecurityExpressionHandler;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;


@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private AccountService accountService;

    public SecurityExpressionHandler expressionHandler(){
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        roleHierarchy.setHierarchy("ROLE_ADMIN > ROLE_USER");
        DefaultWebSecurityExpressionHandler handler = new DefaultWebSecurityExpressionHandler();
        handler.setRoleHierarchy(roleHierarchy);

        return handler;

    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/","/h2-console/**").permitAll()
                .antMatchers("/tables").access("hasAnyRole('ROLE_ADMIN')")
                .antMatchers("/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().ignoringAntMatchers("/h2-console/**")
                .and().headers().frameOptions().sameOrigin()
                .and()
                .formLogin()
                    .loginPage("/login")//post로 요청해야함
                    .defaultSuccessUrl("/index?pageName=mainPage",true)
                    .failureUrl("/fail")
                    .usernameParameter("userId")
                    .passwordParameter("userPwd")
                    .permitAll()
                .and()
                    .logout()//post로 요청해야홤
                    .logoutSuccessUrl("/login")
                    .deleteCookies("JSESSIONID")
                    .clearAuthentication(true)
                    .invalidateHttpSession(true)
                //http  accept header에 html이 있어서 1차적으로 여기서 걸림
                .and()
                .httpBasic();


                 http.rememberMe()                          //id기억하기 쿠키에 토큰 생성
                    .userDetailsService(accountService)
                    .key("remember-me-sample");

                 http.sessionManagement()
                     .maximumSessions(1);
    }


    /**웹으로 들어오는 요청*/
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/**/**/*.js");
        web.ignoring().antMatchers("/**/**/*.css");
        web.ignoring().antMatchers("/**/**/img/*");
        web.ignoring().antMatchers("/**/scss/*");
        web.ignoring().antMatchers("/**/vendor/*");
    }



    //password encoder로 유저정보 저장하기 전에 encoder함
    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
