package dev.jeffersonfreitas.securityapi.dto.user;

import dev.jeffersonfreitas.securityapi.dto.group.GroupResponse;

import java.util.List;


public record UserResponse(String username, List<GroupResponse> groups) {

}
