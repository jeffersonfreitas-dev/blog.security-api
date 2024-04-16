package dev.jeffersonfreitas.securityapi.service.user;

import dev.jeffersonfreitas.securityapi.dto.user.UserCreateRequest;
import dev.jeffersonfreitas.securityapi.dto.user.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse create(UserCreateRequest request);

    List<UserResponse> fetchAll();

    void delete(String id);
}
