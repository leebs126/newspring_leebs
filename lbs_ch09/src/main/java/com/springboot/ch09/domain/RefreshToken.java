package com.springboot.ch09.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access=AccessLevel.PROTECTED)
@Getter
@Setter
@Entity
public class RefreshToken {
	
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id  // 기본키 지정
	@SequenceGenerator(
	    name = "TOKEN_SEQ_GENERATOR",   // 시퀀스 제너레이터 이름
	    sequenceName = "TOKEN_SEQ",     // 실제 오라클 시퀀스 이름
	    initialValue = 1,                 // 시작값
	    allocationSize = 1                // 증가 단위 (1씩 증가)
	)
	@GeneratedValue(
	    strategy = GenerationType.SEQUENCE,
	    generator = "TOKEN_SEQ_GENERATOR"
	)
	@Column(name="id", updatable=false)
	private Long id;
	
	@Column(name="user_id", nullable=false, unique=true)
	private Long userId;
	
	@Column(name="refresh_token", nullable=false)
	private String refreshToken;
	
	
	public RefreshToken(Long userId, String refreshToken) {
		this.userId = userId;
		this.refreshToken = refreshToken;
	}
	
	public RefreshToken update(String newRefreshToken) {
		this.refreshToken = newRefreshToken;
		return this;
	}
	
}







