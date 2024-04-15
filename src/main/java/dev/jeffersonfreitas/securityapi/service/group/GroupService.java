package dev.jeffersonfreitas.securityapi.service.group;

import dev.jeffersonfreitas.securityapi.model.Group;

import java.util.List;

public interface GroupService {
    List<Group> findAllById(List<String> groups);
}
