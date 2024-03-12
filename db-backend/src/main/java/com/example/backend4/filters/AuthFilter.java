package com.example.backend4.filters;

import com.example.backend4.services.AuthService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class AuthFilter extends OncePerRequestFilter {

    @Autowired
    private AuthService authService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (header == null || !isHeaderVaild(header)) {
            filterChain.doFilter(request, response);
            return;
        }

        //UserAuthentication user = authService.parseAuthHeader(header);
        //UserAuthentication auth = authService.authenticateUser(user);
        //SecurityContextHolder.getContext().setAuthentication(auth);

        filterChain.doFilter(request, response);
    }

    private boolean isHeaderVaild(String header) {
        String[] splittedHeader = header.split(" ");

        return splittedHeader.length == 2 && splittedHeader[0].equals("Basic");
    }

}
