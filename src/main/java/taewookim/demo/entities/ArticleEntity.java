package taewookim.demo.entities;

public record ArticleEntity(
        Long id,
        Long writer,
        Long board,
        String title,
        String context,
        String writingdate,
        String editingdate
) {
}
