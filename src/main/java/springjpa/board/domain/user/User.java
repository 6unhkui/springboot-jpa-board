package springjpa.board.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import springjpa.board.domain.BaseTimeEntity;
import springjpa.board.domain.posts.Posts;
//import springjpa.board.domain.user.converter.RoleConverter;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@NoArgsConstructor @Getter
public class User extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column
    private String picture;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<Posts> posts = new ArrayList<>();

    @Builder
    public User(String name, String email, String picture, Role role) {
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
    }

    public User update(String name, String picture){
        this.name = name;
        this.picture = picture;

        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }

}
