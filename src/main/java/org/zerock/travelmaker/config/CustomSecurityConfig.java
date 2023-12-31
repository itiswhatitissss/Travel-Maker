package org.zerock.travelmaker.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.zerock.travelmaker.security.CustomUserDetailService;
import org.zerock.travelmaker.security.handler.Custom403Handler;
import org.zerock.travelmaker.security.handler.CustomSocialLoginSuccessHandler;

import javax.sql.DataSource;

@Configuration
@Log4j2
@EnableMethodSecurity(prePostEnabled = true) //어노테이션으로 권한 설정
@RequiredArgsConstructor
public class CustomSecurityConfig {

    private final DataSource dataSource;
    private final CustomUserDetailService userDetailService;

    @Bean
    public PasswordEncoder passwordEncoder() { //패스워드 암호화
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new CustomSocialLoginSuccessHandler(passwordEncoder());
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        log.info("----------------------configure 인증/인가권한 설정 ---------------------------");

        // 커스텀 로그인 기능
        http.formLogin().loginPage("/travelmaker/user/login").defaultSuccessUrl("/travelmaker/user/loginSuccess",true);  //formLogin() --> 스프링부트가 제공하는 로그인창이 뜸
        // CSRF 비활성화 --> (Cross-Site Request Forgery 크로스 사이트 요청 위조)
        http.csrf().disable(); //login.html을 띄우기 위해선 비활성화를 안해놓으면 로그인이 안됨.

        http.rememberMe()
                .key("12345678")
                .tokenRepository(persistentTokenRepository())
                .userDetailsService(userDetailService)
                .tokenValiditySeconds(60*60*24*30);
        http.exceptionHandling().accessDeniedHandler(accessDeniedHandler());

        http.oauth2Login().loginPage("/travelmaker/user/login").defaultSuccessUrl("/travelmaker/user/loginSuccess").successHandler(authenticationSuccessHandler());

        //로그아웃
        http.logout()
                .logoutUrl("/travelmaker/user/logout")
                .logoutSuccessUrl("/travelmaker/user/login")
                .deleteCookies("JSESSIONID", "remember-me");

        return http.build();
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
        repo.setDataSource(dataSource);
        return repo;
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new Custom403Handler();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        // 정적 자원에 스프링 시큐리티 필터 규칙을 적용하지 않도록 설정
        return (web) -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }
}