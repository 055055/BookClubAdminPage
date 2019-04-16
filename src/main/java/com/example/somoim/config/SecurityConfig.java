package com.example.somoim.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.header.writers.frameoptions.WhiteListedAllowFromStrategy;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;

import java.util.Arrays;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/","/h2-console/**").permitAll()
                .antMatchers("/tables").access("hasAnyRole('ROLE_ADMIN')")
                .anyRequest().authenticated()
                .and()
                .csrf().ignoringAntMatchers("/h2-console/**")
                .and().headers().frameOptions().sameOrigin()
                .and()
                .formLogin()
                //http  accept header에 html이 있어서 1차적으로 여기서 걸림
                .and()
                .httpBasic();
        //accept header에 html이 없다면 여기서 걸림.
      /*  http.logout()
                .logoutUrl("/member")								// 로그아웃 처리 URL(spring security default : /logout)
                .logoutSuccessUrl("/member")							// 로그아웃 성공후 이동할 url
                .invalidateHttpSession(true)							// 로그아웃 성공 후 세션 초기화
                .deleteCookies("JSESSIONID");*/
    }
    //springsecurity는 password를 인코딩을 하고 디코딩을 해서 비밀번호를 관리하는데, 이렇게 하면 비밀번호 encoding안해도된다.
    //보안에 취약하기 때문에 절대 하면 안된다.
/*    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }*/

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
