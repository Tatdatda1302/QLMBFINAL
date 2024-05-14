package com.example.qlbvmb.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

public class MySimpleUrlAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        authorities.forEach(authority -> {
            if(authority.getAuthority().equals("STAFF")) {
                try {
                    redirectStrategy.sendRedirect(request, response, "/staff");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if(authority.getAuthority().equals("ADMIN")) {
                try {
                    redirectStrategy.sendRedirect(request, response, "/admin");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    redirectStrategy.sendRedirect(request, response, "/user");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}