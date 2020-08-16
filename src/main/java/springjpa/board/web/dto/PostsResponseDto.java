package springjpa.board.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import springjpa.board.domain.posts.Posts;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
public class PostsResponseDto {
    private Long id;
    private String title;
    private String content;
    private String author;
    private LocalDateTime createdDate;

    public PostsResponseDto(Posts entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
        this.createdDate = entity.getCreatedDate();
    }
}
