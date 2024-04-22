package dev.jeffersonfreitas.securityapi.service.auth;

import dev.jeffersonfreitas.securityapi.dto.auth.AuthLoginRequest;

public interface AuthService {
    String login(AuthLoginRequest loginRequest);
}
