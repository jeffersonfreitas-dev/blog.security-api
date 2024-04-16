package dev.jeffersonfreitas.securityapi.security;

import dev.jeffersonfreitas.securityapi.model.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class UserPrincipal {

    private String username;
    private Collection<GrantedAuthority> authorities;

    private UserPrincipal(User user){
        this.username = user.getUsername();

        this.authorities = user.getGroups().stream()
                .flatMap(group -> group.getPermissions().stream()
                        .map(perm -> new SimpleGrantedAuthority("ROLE_".concat(perm.getName()))))
                .distinct()
                .collect(Collectors.toList());
    }

    public static UserPrincipal create(User user) {
        return new UserPrincipal(user);
    }
}
