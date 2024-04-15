package dev.jeffersonfreitas.securityapi.model;

import dev.jeffersonfreitas.securityapi.dto.user.UserCreateRequest;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "users")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @EqualsAndHashCode.Include
    private String username;

    private String password;

    @ManyToMany
    @JoinTable(name = "users_groups",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id")
    )
    private List<Group> groups;


    public static User create(UserCreateRequest request, List<Group> groups) {
        return new User(request.getUsername(), request.getPassword(), groups);
    }

    private User(String username, String password, List<Group> groups) {
        this.username = username;
        this.password = password;
        this.groups = groups;
    }
}
