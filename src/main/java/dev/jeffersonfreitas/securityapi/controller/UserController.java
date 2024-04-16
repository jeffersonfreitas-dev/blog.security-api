package dev.jeffersonfreitas.securityapi.controller;

import dev.jeffersonfreitas.securityapi.service.user.UserService;
import dev.jeffersonfreitas.securityapi.dto.user.UserCreateRequest;
import dev.jeffersonfreitas.securityapi.dto.user.UserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse create(@RequestBody @Valid UserCreateRequest request){
        return service.create(request);
    }

    @GetMapping
    @PreAuthorize("hasRole('CLIENT_LIST')")
    @ResponseStatus(HttpStatus.OK)
    public List<UserResponse> fetchAll(){
        return service.fetchAll();
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('CLIENT_DELETE')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id){
        service.delete(id);
    }
}
