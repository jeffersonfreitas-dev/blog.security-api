package dev.jeffersonfreitas.securityapi.service.user;

import dev.jeffersonfreitas.securityapi.dto.user.UserCreateRequest;
import dev.jeffersonfreitas.securityapi.dto.user.UserResponse;

public interface UserService {
    UserResponse create(UserCreateRequest request);
}
