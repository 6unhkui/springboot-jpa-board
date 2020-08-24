package springjpa.board.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import springjpa.board.domain.BaseTimeEntity;
import springjpa.board.domain.user.User;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@NoArgsConstructor @Getter
public class Posts extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(referencedColumnName = "id")
    private User user;

    @Builder
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    // == 연관 관계 편의 메소드 ==
    public void setUser(User user){
        this.user = user;
        user.getPosts().add(this);
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
