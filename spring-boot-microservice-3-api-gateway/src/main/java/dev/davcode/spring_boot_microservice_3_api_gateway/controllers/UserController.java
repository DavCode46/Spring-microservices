package dev.davcode.spring_boot_microservice_3_api_gateway.controllers;

import dev.davcode.spring_boot_microservice_3_api_gateway.entities.Role;
import dev.davcode.spring_boot_microservice_3_api_gateway.security.UserPrincipal;
import dev.davcode.spring_boot_microservice_3_api_gateway.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PutMapping("/change/{role}")
    public ResponseEntity<?> changeRole(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @PathVariable Role role
    ) {
        userService.updateUserRole(role, userPrincipal.getUsername());

        return ResponseEntity.ok(true);
    }

}
