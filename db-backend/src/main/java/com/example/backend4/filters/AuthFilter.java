package com.example.backend4.filters;

import com.example.backend4.model.auth.User;
import com.example.backend4.services.AuthService;
import com.example.backend4.services.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class AuthFilter extends OncePerRequestFilter {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (header == null || !isHeaderValid(header)) {
            filterChain.doFilter(request, response);
            return;
        }

        User user = authService.parseAuthHeader(header);
        UserDetails userDetails;

        try {
            userDetails = userService.loadUserByUsername(user.getUsername());
        }
        catch (UsernameNotFoundException e){
            filterChain.doFilter(request, response);
            return;
        }


        if (!authService.authenticateUser(user)) {
            filterChain.doFilter(request, response);
            return;
        }


        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken
                (userDetails, null, userDetails.getAuthorities());


        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(auth);
        SecurityContextHolder.setContext(context);

        filterChain.doFilter(request, response);
    }

    private boolean isHeaderValid(String header) {
        String[] splitHeader = header.split(":");

        return splitHeader.length == 2;
    }

}
