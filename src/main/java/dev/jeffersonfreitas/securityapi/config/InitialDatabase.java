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

        Permission productCreate = new Permission("PRODUCT_CREATE");
        Permission productList = new Permission("PRODUCT_LIST");
        Permission productDelete = new Permission("PRODUCT_DELETE");
        permissionRepository.saveAll(List.of(productCreate, productList, productDelete));

        Group admin = new Group("ADMIN", List.of(productCreate, productList, productDelete));
        Group user = new Group("USER", List.of(productList));
        groupRepository.saveAll(List.of(admin, user));
    }
}
