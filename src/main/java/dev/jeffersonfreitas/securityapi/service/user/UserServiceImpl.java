package dev.jeffersonfreitas.securityapi.service.user;

import dev.jeffersonfreitas.securityapi.dto.user.UserCreateRequest;
import dev.jeffersonfreitas.securityapi.dto.user.UserResponse;
import dev.jeffersonfreitas.securityapi.exceptions.UserRequestInvalidException;
import dev.jeffersonfreitas.securityapi.model.Group;
import dev.jeffersonfreitas.securityapi.model.User;
import dev.jeffersonfreitas.securityapi.repository.UserRepository;
import dev.jeffersonfreitas.securityapi.service.group.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository repository;
    private final GroupService groupService;

    @Override
    public UserResponse create(UserCreateRequest request) {
        if(request == null)
            throw new UserRequestInvalidException("Request cannot be null");

        List<Group> groups = groupService.findAllById(request.getGroups());
        User entity = User.create(request, groups);
        repository.save(entity);
        return new UserResponse(entity.getUsername());
    }
}
