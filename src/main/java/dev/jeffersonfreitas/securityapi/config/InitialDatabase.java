package dev.jeffersonfreitas.securityapi.config;

import dev.jeffersonfreitas.securityapi.model.Group;
import dev.jeffersonfreitas.securityapi.model.Permission;
import dev.jeffersonfreitas.securityapi.repository.GroupRepository;
import dev.jeffersonfreitas.securityapi.repository.PermissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class InitialDatabase implements CommandLineRunner {

    private final PermissionRepository permissionRepository;
    private final GroupRepository groupRepository;


    @Override
    public void run(String... args) throws Exception {

        Permission productList = new Permission("CLIENT_LIST");
        Permission productDelete = new Permission("CLIENT_DELETE");
        permissionRepository.saveAll(List.of(productList, productDelete));

        Group admin = new Group("ADMIN", List.of(productList, productDelete));
        Group user = new Group("USER", List.of(productList));
        groupRepository.saveAll(List.of(admin, user));
    }
}
