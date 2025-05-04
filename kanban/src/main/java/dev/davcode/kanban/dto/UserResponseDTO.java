package dev.davcode.kanban.dto;

import dev.davcode.kanban.projectMembers.ProjectMember;
import dev.davcode.kanban.tasks.Task;

import java.util.Set;

public record UserResponseDTO(
        String email,
        Set<Task> tasks,
        Set<ProjectMember> memberShips
) {
}
