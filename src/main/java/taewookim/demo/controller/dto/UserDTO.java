package taewookim.demo.controller.dto;

public record UserDTO(
        Long id,
        String nickname,
        String email,
        String pw
) {
}
