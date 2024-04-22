package dev.jeffersonfreitas.securityapi.controller;

import dev.jeffersonfreitas.securityapi.dto.auth.AuthLoginRequest;
import dev.jeffersonfreitas.securityapi.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;


    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public String login(@RequestBody AuthLoginRequest loginRequest) {
        return service.login(loginRequest);
    }
}
