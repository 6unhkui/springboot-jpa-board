package springjpa.board.web.dto;

import lombok.*;
import springjpa.board.domain.posts.Posts;

import javax.validation.constraints.NotEmpty;

@ToString
@Getter @Setter
@NoArgsConstructor
public class PostsSaveRequestDto {

    private Long userId;

    @NotEmpty(message = "제목은 필수입니다.")
    private String title;

    @NotEmpty(message = "글 내용은 필수입니다.")
    private String content;

    @Builder
    public PostsSaveRequestDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Posts toEntity() {
        return Posts.builder().title(title).content(content).build();
    }

}
