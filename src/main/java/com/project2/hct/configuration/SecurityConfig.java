package com.project2.hct.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	
	//비밀번호 암호화
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		
		return new BCryptPasswordEncoder();
	}

	//SecurityFilterChain 이라는 인터페이스를 리턴타입으로 지정
	//내부에는 특정 요청에 대한 처리 해줌 (인가)
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{	
		HttpSessionRequestCache requestCache = new HttpSessionRequestCache();
		requestCache.setMatchingRequestParameterName(null);
		http
			.requestCache(request-> request
				.requestCache(requestCache))
			.authorizeHttpRequests((auth)-> auth
					.requestMatchers("/","/loginForm","/login","/Join","/n_joinForm","/b_joinForm","/b_join","/n_join").permitAll()
					.requestMatchers("/css/**", "/image/**").permitAll()
					.requestMatchers("/admin").hasRole("ADMIN")
					.requestMatchers("/my/**").hasAnyRole("ADMIN", "USER")
					.anyRequest().authenticated()
		);
		
		//권한 필요한 페이지 접속 시 로그인 창이 뜨도록
		http
			.formLogin((auth)->auth.loginPage("/loginForm")
					.loginProcessingUrl("/login")
					.defaultSuccessUrl("/main")
					.usernameParameter("memId")
					.passwordParameter("memPw")
		);
		
		// 로그아웃
		http
				.logout((auth)-> auth.logoutUrl("/logout")
						.logoutSuccessUrl("/"));
		
//		csrf: 위조 방지용 > 원래 login 시 csrf 토큰도 보내주어야 로그인이 정상적으로 진행이 됨 > 개발단계에서는 일단 이 설정을 off해둠
		http
				.csrf((auth)-> auth.disable());
		
		// 다중 로그인 관련 처리
		http	
				.sessionManagement((auth) -> auth
				.maximumSessions(1)						//하나의 아이디에서 중복 로그인 가능하도록 하는 갯수
				.maxSessionsPreventsLogin(true));		//위에서 설정한 갯수 초과할 경우 처리 > true:새로운 로그인 차단
		
		// 세션 고정 공격 보호
		http
				.sessionManagement((auth)-> auth
						.sessionFixation().changeSessionId());		//로그인 시 동일한 세션에 대한 id변경
		
		
		return http.build();
	}
	
	
}
