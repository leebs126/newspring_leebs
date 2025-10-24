package com.springboot.ch09.config.jwt;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Duration;
import java.util.Collections;
import java.util.Date;
import java.util.Set;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import com.springboot.ch09.domain.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TokenProvider {
	private final JwtProperties jwtProperties;
	
	public String generateToken(User user, Duration expiredAt) {
			Date now =new Date();
			return makeToken(new Date(now.getTime() + expiredAt.toMillis()), user);
	}
	
	
	//1: JWT 토큰 생성 메서드
	private String makeToken(Date expiry, User user) {
		Date now = new Date();
		// ✅ 1. Key 객체를 명시적으로 생성 (HS256용)
	    Key key = Keys.hmacShaKeyFor(jwtProperties.getSecretKey().getBytes(StandardCharsets.UTF_8));

		
		return Jwts.builder()
				.setHeaderParam(Header.TYPE, Header.JWT_TYPE)
				//내용 iss : leebs126@gmail.com(properties 파일에 설정한 값)
				.setIssuer(jwtProperties.getIssuer()) 
				.setIssuedAt(now)					//내용 iat : 현재 시간
				.setExpiration(expiry)				//내용 exp : expiry 멤버 변숫값 
				.setSubject(user.getEmail())		//내용 sub : 유저의 이메일
				.claim("id", user.getId())			//클레임 id : 유저 id
				//서명: 비밀값과 함깨 해시값을 HS256 방식으로 암호화
	            .signWith(key, SignatureAlgorithm.HS256)
	            .compact();
	}
	
	//2: JWT 토큰 유효성 검증 메서드
	public boolean validToken(String token) {
		try {
			// ✅ 1. Key 객체 생성 (생성 시점 통일)
			Key key = Keys.hmacShaKeyFor(jwtProperties.getSecretKey().getBytes(StandardCharsets.UTF_8));

			// ✅ 2. 최신 구문: parserBuilder() + setSigningKey(key) + build()
			Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token); // ✅ 실제 검증 (유효하면 예외 없음)

			return true;
		} catch (Exception e) {
			// 만료, 위조, 형식 오류 등 모든 예외는 false 처리
			return false;
		}
	}
	
	//3: 토큰 기반으로 인증 정보를 가져오는 메서드
	public Authentication getAuthentication(String token) {
		Claims claims = getClaims(token);
		Set<SimpleGrantedAuthority> authorities = Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
		return new UsernamePasswordAuthenticationToken(new org.springframework.security.core.userdetails.User
				(claims.getSubject(), "", authorities), token, authorities);
	}
	
	//4: 토큰 기반으로 유저 ID를 가져오는 메서드
	public Long getUserId(String token) {
		Claims claims = getClaims(token);
		return claims.get("id", Long.class);
	}
	
	private Claims getClaims(String token) {
	    // ✅ 1. Key 객체 생성 (generateToken()에서 사용한 것과 반드시 동일하게)
	    Key key = Keys.hmacShaKeyFor(jwtProperties.getSecretKey().getBytes(StandardCharsets.UTF_8));

	    // ✅ 2. 최신 문법: parserBuilder() → setSigningKey(key) → build() → parseClaimsJws()
	    return Jwts.parserBuilder()
	            .setSigningKey(key)
	            .build()
	            .parseClaimsJws(token)
	            .getBody(); // ✅ 클레임(Claims) 추출
	}
}











