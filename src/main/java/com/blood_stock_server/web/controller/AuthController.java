package com.blood_stock_server.web.controller;

import com.blood_stock_server.business.service.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final TokenService tokenService;

    @PostMapping("/token")
    public String token(Authentication authentication){
        log.debug("Token requested for user: {}", authentication.getName());
        String token = tokenService.generateToken(authentication);
        log.debug("Token granted {}", token);
        return token;
    }
}
