package dev.davcode.spring_boot_microservice_3_api_gateway.services;

import dev.davcode.spring_boot_microservice_3_api_gateway.entities.Role;
import dev.davcode.spring_boot_microservice_3_api_gateway.entities.User;
import dev.davcode.spring_boot_microservice_3_api_gateway.repositories.UserRepository;
import dev.davcode.spring_boot_microservice_3_api_gateway.security.jwt.JwtProvider;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public User saveUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.USER);
        user.setFechaCreacion(LocalDateTime.now());

        User userCreated = userRepository.save(user);

        String jwt = jwtProvider.generateToken(userCreated);

        userCreated.setToken(jwt);

        return userCreated;
    }

    @Override
    public Optional<User> findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    @Transactional
    @Override
    public void updateUserRole(Role newRole, String username) {
        userRepository.updateUserRole(username, newRole);
    }

}
