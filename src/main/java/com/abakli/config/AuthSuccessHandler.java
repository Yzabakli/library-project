package com.abakli.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@Component
public class AuthSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {

        Set<String> roles =
                AuthorityUtils.authorityListToSet(authentication.getAuthorities());

        if (roles.contains("Admin")) {

            response.sendRedirect("/staff/create");
        }

        if (roles.contains("Staff")) {

            response.sendRedirect("/book/create");
        }

        if (roles.contains("Student")) {

            response.sendRedirect("/book");
        }
    }
}
