package com.vladislavk.testcrud.controller;

import com.vladislavk.testcrud.configuration.jwt.JWTTokenAuthService;
import com.vladislavk.testcrud.model.AuthToken;
import com.vladislavk.testcrud.model.LoginUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Vladislav Klochkov
 * @project testcrud
 * @date 10.06.2019
 */

@RestController
public class AuthenticationController {
    private static Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTTokenAuthService jwtTokenAuthService;

    @PostMapping(value = "/get-token")
    public ResponseEntity getToken(@RequestBody LoginUser loginUser) throws AuthenticationException {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUser.getUsername(),
                        loginUser.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = jwtTokenAuthService.addAuthentication(authentication);
        logger.debug("AuthToken: {}", token);
        return ResponseEntity.ok(new AuthToken(token));
    }
}
