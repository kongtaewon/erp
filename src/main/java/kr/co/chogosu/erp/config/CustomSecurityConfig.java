package kr.co.chogosu.erp.config;

import kr.co.chogosu.erp.security.CustomUserDetailsService;
import kr.co.chogosu.erp.security.handler.Custom403Handler;
import kr.co.chogosu.erp.security.handler.CustomSocialLoginSuccessHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Log4j2
@Configuration
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class CustomSecurityConfig {

    //주입 필요
    private final DataSource dataSource;
    private final CustomUserDetailsService userDetailsService;

    // PasswordEncoder가 있어야 함 이를 제공
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    //로그인 성공 처리시 이용 OAuth2
    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new CustomSocialLoginSuccessHandler(passwordEncoder());
    }


    // 필터 체인으로 일반 보기에서는 로그인 일단 패스
    // 로그인 화면에서 로그인을 진행 함을 정의
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        log.info("------------------------- configure -------------------");

        //커스텀 로그인 페이지
        http.formLogin().loginPage("/member/login");
        //CSRF  토큰 비활성화
        http.csrf().disable(); // csrf 무시하고 아이디와 패스워드로만 로그인 추후 삭제할듯

        http.rememberMe()
                .key("12345678")
                .tokenRepository(persistentTokenRepository())
                .userDetailsService(userDetailsService)
                .tokenValiditySeconds(60*60*24*30);

        http.exceptionHandling().accessDeniedHandler(accessDeniedHandler()); //403

        http.oauth2Login().loginPage("/member/login");

        // 소셜 로그인 추가부분 761
        http.oauth2Login()
                .loginPage("/member/login")
                .successHandler(authenticationSuccessHandler());;

        return http.build();
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new Custom403Handler();
    }

    // 정적인 자원들은 스프링 시큘티 적용 제외
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {

        log.info("--------------------- web configure ---------------------");

        return (web) -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {

        JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
        repo.setDataSource(dataSource);
        return repo;

    }


}
