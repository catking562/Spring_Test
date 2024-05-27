package taewookim.demo.entities;

public record UserEntity(
        Long id,
        String nickname,
        String email,
        String pw
) {
}
