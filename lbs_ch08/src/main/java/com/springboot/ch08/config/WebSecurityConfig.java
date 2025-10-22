
package com.springboot.ch08.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.springboot.ch08.service.UserDetailService;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final UserDetailService userService;

    // ✅ 1. H2 콘솔 및 정적 리소스 보안 제외
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
//                .requestMatchers(PathRequest.toH2Console())
                .requestMatchers("/static/**", "/css/**", "/js/**", "/images/**");
    }

    // ✅ 2. 비밀번호 암호화기 등록
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // ✅ 3. AuthenticationManager Bean 등록 (Spring이 자동 구성 사용)
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    // ✅ 4. HTTP 보안 설정 (최신 Spring Security 6 스타일)
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // 🔒 요청별 접근 권한 설정
//            .authorizeHttpRequests(auth -> auth
//                .requestMatchers("/login", "/signup", "/user", "/h2-console/**").permitAll()
//                .anyRequest().authenticated()
//            )

            .authorizeHttpRequests(auth -> auth
                // 로그인, 회원가입, 사용자 등록은 모두 허용
                .requestMatchers("/login", "/signup", "/user").permitAll()
                // 게시글 목록(/articles)과 게시글 상세(/articles/{id})도 모두 허용
                .requestMatchers("/articles", "/articles/**").permitAll()
                // 단, 새 글 작성(/articles/new)만 인증 필요
                .requestMatchers("/articles/new").authenticated()
                // 그 외 모든 요청은 인증 필요
                .anyRequest().authenticated()
                )
            // 🔑 폼 로그인 설정
            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/articles", true)
                .permitAll()
            )

            // 🚪 로그아웃 설정
            .logout(logout -> logout
                .logoutSuccessUrl("/articles?logout")
                .invalidateHttpSession(true)
                .permitAll()
            )

            // 🧱 H2 콘솔 접근 허용 (frame 허용)
            .headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()))

            // 🚫 CSRF 예외 처리
            .csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**"));

        return http.build();
    }
}


//package com.springboot.ch08.config;
//
//import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.ProviderManager;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//import com.springboot.ch08.service.UserDetailService;
//
//import lombok.RequiredArgsConstructor;
//
//@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor
//public class WebSecurityConfig {
//	
//	private final UserDetailService userService;
//	
//	//1: 스프링 시큐리티 기능 비활성화
//	@Bean
//	public WebSecurityCustomizer configure() {
//		return (web)-> web.ignoring()
//				.requestMatchers(PathRequest.toH2Console())
//				.requestMatchers("/static/**");
//	}
//	
//	
//	//2: 특정 HTTP 요청에 대한 웹 기반 보안 구성
//	@Bean
//	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//		return http
//				.authorizeHttpRequests(auth -> auth
//						.requestMatchers("/login"
//										,"/signup"
//										,"/user"
//								).permitAll()
//								 .anyRequest().authenticated())
//				.formLogin(formLogin->formLogin   //4: 폼 기반 로그인 설정
//					  .loginPage("/login")
//					  .defaultSuccessUrl("/articles")
//					  )
//					  .logout(logout->logout     //5: 로그아웃 설정
//							  .logoutSuccessUrl("/logout")
//							  .invalidateHttpSession(true)
//					   )
//					  .csrf(AbstractHttpConfigurer ::disable)
//					  .build();
//	}
//	
//	//7: 인증 관리자 관련 설정
//	@Bean
//	public AuthenticationManager authenticationManager(HttpSecurity http,
//			BCryptPasswordEncoder bCryptPasswordEncoder, UserDetailService userDetailService) {
//		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//		authProvider.setUserDetailsService(userService);
//		authProvider.setPasswordEncoder(bCryptPasswordEncoder);
//		return new ProviderManager(authProvider);
//	}
//	
//	//패스워드 인코더로 사용할 빈 등록
//	@Bean
//	public BCryptPasswordEncoder bCryptPasswordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//}
//
//
//
//
//
//
//
//
//
//
//
//
