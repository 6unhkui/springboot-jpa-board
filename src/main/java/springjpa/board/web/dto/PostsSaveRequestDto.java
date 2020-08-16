package springjpa.board.web.dto;

import lombok.*;
import net.bytebuddy.implementation.bind.annotation.Empty;
import org.springframework.validation.annotation.Validated;
import springjpa.board.domain.posts.Posts;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
@NoArgsConstructor
public class PostsSaveRequestDto {

    @NotEmpty(message = "제목은 필수입니다.")
    private String title;

    @NotEmpty(message = "글 내용은 필수입니다.")
    private String content;

    @NotEmpty(message = "작성자 이름은 필수입니다.")
    private String author;

    @Builder
    public PostsSaveRequestDto(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Posts toEntity() {
        return Posts.builder().title(title).content(content).author(author).build();
    }

    @Override
    public String toString() {
        return "PostsSaveRequestDto{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
