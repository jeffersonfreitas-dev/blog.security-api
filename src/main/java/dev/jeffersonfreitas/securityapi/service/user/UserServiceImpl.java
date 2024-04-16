package dev.jeffersonfreitas.securityapi.service.user;

import dev.jeffersonfreitas.securityapi.dto.group.GroupResponse;
import dev.jeffersonfreitas.securityapi.dto.user.UserCreateRequest;
import dev.jeffersonfreitas.securityapi.dto.user.UserResponse;
import dev.jeffersonfreitas.securityapi.exceptions.UserNotFoundException;
import dev.jeffersonfreitas.securityapi.exceptions.UserRequestInvalidException;
import dev.jeffersonfreitas.securityapi.model.Group;
import dev.jeffersonfreitas.securityapi.model.User;
import dev.jeffersonfreitas.securityapi.repository.UserRepository;
import dev.jeffersonfreitas.securityapi.service.group.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository repository;
    private final GroupService groupService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponse create(UserCreateRequest request) {
        if(request == null)
            throw new UserRequestInvalidException("Request cannot be null");

        List<Group> groups = groupService.findAllById(request.getGroups());
        var passwordEncoded = passwordEncoder.encode(request.getPassword());
        User entity = User.create(request.getUsername(), passwordEncoded, groups);
        repository.save(entity);
        return new UserResponse(entity.getUsername(), GroupResponse.fromEntity(entity.getGroups()));
    }

    @Override
    public List<UserResponse> fetchAll() {
        return repository.findAll().stream()
                .map(u -> new UserResponse(u.getUsername(), GroupResponse.fromEntity(u.getGroups()))).toList();
    }

    @Override
    public void delete(String id) {
        User user = findById(id);
        repository.delete(user);
    }

    private User findById(String id) {
        if(id == null || id.isEmpty())
            throw new UserRequestInvalidException("User id cannot be null or empty");

        return repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found by id: " + id));
    }
}
