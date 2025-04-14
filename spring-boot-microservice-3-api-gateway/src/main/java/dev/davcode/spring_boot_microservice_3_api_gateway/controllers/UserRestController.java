package dev.davcode.spring_boot_microservice_3_api_gateway.controllers;

import dev.davcode.spring_boot_microservice_3_api_gateway.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRestController {

    @Autowired
    private UserService userService;

}
