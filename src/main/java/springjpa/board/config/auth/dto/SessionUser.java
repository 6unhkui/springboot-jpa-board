package springjpa.board.config.auth.dto;

import lombok.Getter;
import springjpa.board.domain.user.User;

import java.io.Serializable;

/**
 * 세션에 사용자 정보를 저장하기 위한 DTO 클래스
 */

@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
