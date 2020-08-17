package springjpa.board.config.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import springjpa.board.domain.user.Role;

@RequiredArgsConstructor
@EnableWebSecurity // spring security 설정들을 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .headers().frameOptions().disable()
            .and()
                .authorizeRequests() // URL 별 권한 관리를 설정하는 옵션의 시작점
                    .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll()
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                    .anyRequest().authenticated()
            .and()
                .logout() // 로그아웃 기능에 대한 여러 설정의 진입점
                    .logoutSuccessUrl("/")
            .and()
                .oauth2Login() // OAuth2 로그인 기능에 대한 여러 설정의 진입점
                    .userInfoEndpoint() // OAuth2 로그인 성공 이후 사용자 정보를 가져올 때의 설정들 담당
                    .userService(customOAuth2UserService); // 소셜 로그인 성공 시 후속 조치를 진행할 OAuth2UserService 인터페이스의 구현체
    }
}
