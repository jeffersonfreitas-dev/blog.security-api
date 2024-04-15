package dev.jeffersonfreitas.securityapi.service.group;

import dev.jeffersonfreitas.securityapi.exceptions.GroupNotFoundException;
import dev.jeffersonfreitas.securityapi.exceptions.GroupRequestInvalidException;
import dev.jeffersonfreitas.securityapi.model.Group;
import dev.jeffersonfreitas.securityapi.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService{

    private final GroupRepository repository;

    @Override
    public List<Group> findAllById(List<String> groups) {
        if(groups == null || groups.isEmpty())
            throw new GroupRequestInvalidException("Groups cannot be null or empty");

        return groups.stream().map(this::findById).toList();
    }

    private Group findById(String id){
        if(id == null || id.isEmpty())
            throw new GroupRequestInvalidException("Group id cannot be null or empty");

        return repository.findById(id)
                .orElseThrow(() -> new GroupNotFoundException("Group not found by id: " + id));
    }
}
