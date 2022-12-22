package com.abakli.config;

import com.abakli.service.SecurityService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig {

    private final SecurityService securityService;
    private final AuthSuccessHandler authSuccessHandler;

    public SecurityConfig(SecurityService securityService, AuthSuccessHandler authSuccessHandler) {
        this.securityService = securityService;
        this.authSuccessHandler = authSuccessHandler;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http.authorizeRequests()
                .antMatchers("/staff/update/**")
                .hasAnyAuthority("Admin", "Staff")
                .antMatchers("/staff/**")
                .hasAuthority("Admin")
                .antMatchers("/student/create", "/record/return-records", "/book/create", "/book/update")
                .hasAuthority("Staff")
                .antMatchers("/book/return-history")
                .hasAuthority("Student")
                .antMatchers("/book/**")
                .hasAnyAuthority("Student", "Staff")
                .antMatchers("/login", "/fragments/**", "/assets/**", "/images/**", "/student/create-profile", "/student/create", "/api/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
//                .httpBasic()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/welcome")
                .successHandler(authSuccessHandler)
                .failureUrl("/login?error=true")
                .permitAll()
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login")
                .and()
                .rememberMe().tokenValiditySeconds(120)
                .key("abakli")
                .userDetailsService(securityService)
                .and()
                .build();
    }
}
