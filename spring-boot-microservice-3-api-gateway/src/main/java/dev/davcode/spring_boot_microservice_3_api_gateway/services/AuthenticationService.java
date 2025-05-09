package dev.davcode.spring_boot_microservice_3_api_gateway.services;

import dev.davcode.spring_boot_microservice_3_api_gateway.entities.User;

public interface AuthenticationService {
    User signInAndReturnJWT(User signInRequest);
}
