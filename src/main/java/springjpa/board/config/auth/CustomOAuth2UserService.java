package springjpa.board.config.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import springjpa.board.config.auth.dto.OAuthAttributes;
import springjpa.board.config.auth.dto.SessionUser;
import springjpa.board.domain.user.User;
import springjpa.board.domain.user.UserRepository;

import javax.servlet.http.HttpSession;
import java.util.Collections;


/**
 * 구글 로그인 이후 가져온 사용자 정보(email, name, picture 등)들을 기반으로
 * 가입 및 정보 수정, 세션 저장 등의 기능을 지원하는 클래스
 */

@RequiredArgsConstructor
@Service
@Slf4j
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final UserRepository userRepository;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        // 현재 로그인 진행중인 서비스를 구분하는 코드
        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        // OAuth2 로그인 진행 시 키가 되는 필드 값. Primary Key와 같은 의미임
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails()
                                                  .getUserInfoEndpoint().getUserNameAttributeName();

        // OAuth2UserService를 통해 가져온 OAuth2User의 attribute를 담을 클래스
        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

        User user = saveOrUpdate(attributes);

        httpSession.setAttribute("user", new SessionUser(user));
        // SessionUser : 세션에 사용자 정보를 저장하기 위한 DTO 클래스

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())),
                                      attributes.getAttributes(),
                                      attributes.getNameAttributeKey());
    }

    private User saveOrUpdate(OAuthAttributes attributes) {
        // 이메일과 일치하는 사용자를 검색하고, 존재한다면 사용자의 이름이나 프로필 사진이 변경될 때를 대비해 update 시켜줌
        User user = userRepository.findByEmail(attributes.getEmail())
                                   .map(entity -> entity.update(attributes.getName(), attributes.getPicture()))
                                   .orElse(attributes.toEntity());
        return userRepository.save(user);
    }
}
