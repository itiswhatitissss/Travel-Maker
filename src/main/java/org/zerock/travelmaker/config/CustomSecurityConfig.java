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
import org.zerock.travelmaker.security.CustomUserDetailService;
import org.zerock.travelmaker.security.handler.Custom403Handler;

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

//    @Bean
//    public AuthenticationSuccessHandler authenticationSuccessHandler() {
//        return new CustomSocialLoginSuccessHandler(passwordEncoder());
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        log.info("----------------------configure 인증/인가권한 설정 ---------------------------");

//        http
//                .formLogin().loginPage("/travelmaker/login")
//                .defaultSuccessUrl("/travelmaker/loginSuccess", true);
        http
                .authorizeRequests()
                    .antMatchers("/travelmaker/login").permitAll()
                    .anyRequest().authenticated()
                    .and()
                .formLogin()
                    .loginPage("/travelmaker/login")
                    .defaultSuccessUrl("/travelmaker/loginSuccess", true)
                    .permitAll()
                    .and()
                .logout()
                    .logoutUrl("/travelmaker/logout")
                    .logoutSuccessUrl("/travelmaker/login")
                    .deleteCookies("JSESSIONID", "remember-me")
                    .permitAll();

        http.csrf().disable(); //csrf토큰 비활성화

        http
                .rememberMe() //자동로그인
                .key("12345678")
                .tokenRepository(persistentTokenRepository())
                .userDetailsService(userDetailService)
                .tokenValiditySeconds(60*60*24*30);

        http.exceptionHandling().accessDeniedHandler(accessDeniedHandler()); //403

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
        log.info("------------web configure-------------------");

        return (web) -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }
}