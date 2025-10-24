package com.springboot.ch08.config.jwt;

import java.time.Duration;
import java.util.Date;
import java.util.Map;

import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.Builder;
import lombok.Getter;
import static java.util.Collections.emptyMap;

import java.nio.charset.StandardCharsets;
import java.security.Key;


@Getter
public class JwtFactory {
	private String subject = "test@gmail.com";
	private Date issueAt = new Date();
	private Date expiration =new Date(new Date().getTime() + Duration.ofDays(14).toMillis());
	private Map<String, Object> claims = emptyMap();
	
	//빌더 패턴을 사용해 설정이 필요한 데이터만 선택 설정
	@Builder
	public JwtFactory(String subject, Date issueAt, Date expiration, Map<String ,Object> claims) {
		this.subject = subject != null ? subject : this.subject;
		this.issueAt = issueAt != null ? issueAt : this.issueAt;
		this.expiration = expiration != null ? expiration : this.expiration;
		this.claims = claims != null ? claims : this.claims;
	}

	
	public static JwtFactory withDefaultValues() {
		return JwtFactory.builder().build();
	}
	
	//jjwt 라이브러리를 사용해 JWT 토큰 생성
	public String createToken(JwtProperties jwtProperties) {
		// ✅ 1. Key 객체를 명시적으로 생성 (HS256용)
	    Key key = Keys.hmacShaKeyFor(jwtProperties.getSecretKey().getBytes(StandardCharsets.UTF_8));

		return Jwts.builder()
				.setSubject(subject)
				.setHeaderParam(Header.TYPE, Header.JWT_TYPE)
				.setIssuer(jwtProperties.getIssuer())
				.setIssuedAt(issueAt)
				.setExpiration(expiration)
//				.setClaims(claims)
				 .addClaims(claims) // ✅ 기존 표준 클레임 덮어쓰지 않음
				.signWith(key, SignatureAlgorithm.HS256)
				.compact();
	}
	
}






