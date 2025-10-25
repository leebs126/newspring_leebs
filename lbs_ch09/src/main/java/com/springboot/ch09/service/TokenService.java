package com.springboot.ch09.service;

import java.time.Duration;

import org.springframework.stereotype.Service;

import com.springboot.ch09.config.jwt.TokenProvider;
import com.springboot.ch09.domain.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TokenService {
	private final TokenProvider tokenProvider;
	private final RefreshTokenService refreshTokenService;
	private final UserService userService;
	
	public String createNewAccessToken(String refreshToken) {
		//토큰 유효성 검사에 실패하면 얘외 발생
		if(!tokenProvider.validToken(refreshToken)) {
			throw new IllegalArgumentException("Unexpected user!");
		}
		
		Long userId = refreshTokenService.findByRefreshToken(refreshToken).getUserId();
		User user = userService.findById(userId);
		
		return tokenProvider.generateToken(user, Duration.ofHours(2));
	}
}
