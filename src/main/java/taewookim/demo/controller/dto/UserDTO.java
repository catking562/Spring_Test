package taewookim.demo.controller.dto;

public record UserDTO(
        Long id,
        String name,
        String email,
        String password
) {
}
