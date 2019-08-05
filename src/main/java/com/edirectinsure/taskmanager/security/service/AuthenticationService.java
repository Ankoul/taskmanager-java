package com.edirectinsure.taskmanager.security.service;

import com.edirectinsure.taskmanager.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

@Service
public class AuthenticationService {

    private final JwtAuthenticationService authenticationService;

    @Autowired
    public AuthenticationService(JwtAuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    public Map authenticate(HttpServletRequest request, HttpServletResponse response) {
        return authenticationService.authenticate(request, response);
    }

    public Authentication authenticate(HttpServletRequest request) {
        return authenticationService.authenticate(request);
    }

    public User getAuthenticatedUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (User) authentication.getPrincipal();
    }
}
