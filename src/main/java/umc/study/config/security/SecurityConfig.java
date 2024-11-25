package umc.study.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

//보안 정책 정의하는 곳
//spring security의 filter Chain 을 이용하여 http 요청을 필터리하고 인증, 인가 로직을 처리
//SecurityConfig 는 필터 체인과 보안 정책을 설정하는 역할

//spring security 설정을 활성화 시키는 어노테이션
@EnableWebSecurity
@Configuration
public class SecurityConfig {

    //httpsecurity 객체를 통해 ㄷ양한 보안 설정 구성 가능
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //.authorizeHttpRequests() 는 Http 요청에 대한 접근 제어를 설정
        //.requestMatchers : URL 패턴에 접근 권한 설정
        //permitALL 은 인증 없이 접근 가능한 경로를 말함
        //hasRole 해당 역할이 주어진 사람만 접근 가능하도록 함
        //anyREquest.authenticated() 는 그 외 모든 요청에 대해 인증 요구를 해야함
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/", "/home", "/signup", "/css/**").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/home", true)
                        .permitAll()
                )
                .logout((logout) -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                );

        return http.build();
    }

    //비밀번호 암호화하여 저장하기 위해 BCryptPasswordEncoder를 사용
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}