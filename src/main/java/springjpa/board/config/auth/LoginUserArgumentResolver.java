package springjpa.board.config.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import springjpa.board.config.auth.dto.SessionUser;

import javax.servlet.http.HttpSession;

/**
 * HandlerMethodArgumentResolver
 * : 조건에 맞는 경우 메소드가 있다면, 해당 인터페이스의 구현체가 지정한 값으로 메소드의 파라미터를 넘길 수 있다.
 */
@RequiredArgsConstructor
@Component
public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {

    private final HttpSession httpSession;

    // 컨트롤러 메소드에 특정 파라미터를 지원하는지 판단
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean isLoginUserAnnotation = parameter.getParameterAnnotation(LoginUser.class) != null; // @LoginUser 어노테이션이 붙어있고
        boolean isUserClass = SessionUser.class.equals(parameter.getParameterType()); // SessionUser 클래스인 경우

        return isLoginUserAnnotation && isUserClass;
    }

    // 파라미터에 전달할 객체 생성
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        return httpSession.getAttribute("user"); // 세션에서 객체를 가져옴
    }
}
