package dev.davcode.kanban.users;

import dev.davcode.kanban.dto.UserResponseDTO;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

    private UserRepository userRepository;

    @GetMapping
    public List<UserResponseDTO> getUsers() {
        final var users = userRepository.findAll();

        return users.stream()
                .map(user -> new UserResponseDTO(
                        user.getEmail(),
                        new HashSet<>(),
                        new HashSet<>()))
                .toList();
    }
}
