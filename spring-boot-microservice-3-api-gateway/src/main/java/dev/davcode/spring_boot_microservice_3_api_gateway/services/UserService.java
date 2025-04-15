package dev.davcode.spring_boot_microservice_3_api_gateway.services;

import dev.davcode.spring_boot_microservice_3_api_gateway.entities.Role;
import dev.davcode.spring_boot_microservice_3_api_gateway.entities.User;

import java.util.Optional;

public interface UserService {
    User saveUser(User user);

    Optional<User> findByUsername(String username);

    void updateUserRole(Role newRole, String username);

    User findByUsernameReturnToken(String username);
}
