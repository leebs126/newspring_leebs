package com.springboot.ch09.service;

import org.springframework.stereotype.Service;

import com.springboot.ch09.domain.RefreshToken;
import com.springboot.ch09.repository.RefreshTokenRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RefreshTokenService {
	private final RefreshTokenRepository refreshTokenRepository;
	
	public RefreshToken findByRefreshToken(String refreshToken) {
		return refreshTokenRepository.findByRefreshToken(refreshToken)
				.orElseThrow(() -> new IllegalArgumentException("Unexpected user!"));
	}
}
