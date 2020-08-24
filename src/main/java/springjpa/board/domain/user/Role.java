package springjpa.board.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum Role {
    USER("ROLE_USER"), ADMIN("ROLE_ADMIN");

    private final String key;

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
