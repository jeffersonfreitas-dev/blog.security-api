package dev.jeffersonfreitas.securityapi.dto.group;

import dev.jeffersonfreitas.securityapi.model.Group;

import java.util.List;

public record GroupResponse(String name) {

    public static List<GroupResponse> fromEntity(List<Group> groups) {
        return groups.stream().map(GroupResponse::fromEntity).toList();
    }

    public static GroupResponse fromEntity(Group group) {
        return new GroupResponse(group.getName());
    }
}
