package springjpa.board.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum Role {
    GUEST("ROLE_GUEST", "손님"), USER("ROLE_USER", "일반 사용자");

    private final String key;
    private final String title;

//    private final int value;
//
//    Role(int value) {
//        this.value = value;
//    }
//
//    public static Role findByValue(int value){
//        return Arrays.stream(Role.values())
//                .filter(v -> v.getValue() == value)
//                .findAny().orElse(null);
//    }
//
//    public int getValue() {
//        return this.value;
//    }

}
