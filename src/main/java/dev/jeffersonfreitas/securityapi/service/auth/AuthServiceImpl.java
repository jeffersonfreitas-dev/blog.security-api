package dev.jeffersonfreitas.securityapi.service.auth;

import dev.jeffersonfreitas.securityapi.dto.auth.AuthLoginRequest;
import dev.jeffersonfreitas.securityapi.exceptions.NotAuthorizedException;
import dev.jeffersonfreitas.securityapi.model.User;
import dev.jeffersonfreitas.securityapi.repository.UserRepository;
import dev.jeffersonfreitas.securityapi.security.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final UserRepository userRepository;
    @Override
    public String login(AuthLoginRequest loginRequest) {
        if(loginRequest == null)
            throw new IllegalArgumentException("login request must not be null");

        Optional<User> optUser = userRepository.findByUsernameIgnoreCase(loginRequest.username());
        if(optUser.isEmpty())
            throw new NotAuthorizedException("User not exists");

        var passIsValid = SecurityUtils.isValidPassword(loginRequest.password(), optUser.get().getPassword());

        if(passIsValid){
            return SecurityUtils.encode(optUser.get());
        }else {
            throw new NotAuthorizedException("User not authorized");
        }
    }
}
