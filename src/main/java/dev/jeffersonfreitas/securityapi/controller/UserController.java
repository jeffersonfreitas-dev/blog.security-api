package dev.jeffersonfreitas.securityapi.controller;

import dev.jeffersonfreitas.securityapi.service.user.UserService;
import dev.jeffersonfreitas.securityapi.dto.user.UserCreateRequest;
import dev.jeffersonfreitas.securityapi.dto.user.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse create(@RequestBody UserCreateRequest request){
        return service.create(request);
    }
}
