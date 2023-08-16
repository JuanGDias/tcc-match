package com.ufcg.tccmatch.config;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

@Component
public class JwtAuthenticationEntryPointConf implements AuthenticationEntryPoint, Serializable {
    private static final long serialVersionUID = -7858869558953243875L;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        if(authException instanceof InsufficientAuthenticationException) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
        } else if (authException instanceof BadCredentialsException) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Bad Credentials");
        }
    }

}
