package com.example.backend4.controller;

import java.util.List;

import com.example.backend4.model.auth.UserAuthentication;
import com.example.backend4.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.backend4.model.request.AreaCheckRequest;
import com.example.backend4.model.response.AreaCheckResponse;
import com.example.backend4.services.AreaService;

@RestController
@RequestMapping(path = "/api/area")
public class AreaController {

    @Autowired
    private AreaService areaService;

    @Autowired
    private AuthService authService;

    @GetMapping("/areas")
    @CrossOrigin
    public List<AreaCheckResponse> getUserAreas(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {
        UserAuthentication user = authService.authenticateUser(authService.parseAuthHeader(authHeader));
        boolean isAuthenticated = user.isAuthenticated();
        if(isAuthenticated){
            List<AreaCheckResponse> res = areaService.getUserAreas(user.getUser());
            return res;
        }
        else {
            return null;
        }
    }

    @PostMapping("/checkPoint")
    public ResponseEntity<Object> checkArea(@RequestBody AreaCheckRequest request, @RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {
        UserAuthentication user = authService.authenticateUser(authService.parseAuthHeader(authHeader));
        boolean isAuthenticated = user.isAuthenticated();
        if(isAuthenticated){
            areaService.handleRequest(request, user.getUser().getId());
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

}
