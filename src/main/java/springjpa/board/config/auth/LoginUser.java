package springjpa.board.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER) // 어노테이션이 생성 될 수 있는 위치. PARAMETER로 설정했으므로 메소드의 파라미터로 선언된 객체에만 사용 가능
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginUser {  // @interface : 어노테이션 클래스로 지정. LoginUser 라는 이름의 어노테이션이 생성 된 것
}
