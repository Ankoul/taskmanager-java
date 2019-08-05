package com.edirectinsure.taskmanager.controller;

import com.edirectinsure.taskmanager.model.User;
import com.edirectinsure.taskmanager.security.service.AuthenticationService;
import com.edirectinsure.taskmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Map;


@RestController
@Transactional(readOnly = true)
@RequestMapping(value = "/authentication")
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private UserService userService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService, UserService userService) {
        this.authenticationService = authenticationService;
        this.userService = userService;
    }

    @ResponseBody
    @RequestMapping(value = "/login", method = {RequestMethod.POST, RequestMethod.GET}, produces = "application/json")
    public Map authenticate(HttpServletRequest request, HttpServletResponse response) {
        return authenticationService.authenticate(request, response);
    }

    @ResponseBody
    @Transactional
    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = "application/json")
    public User register(@RequestBody @Valid User user) {
        return userService.create(user);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/validate", method = RequestMethod.GET)
    public String validate() {
        return "{\"valid\":true}";
    }

}
