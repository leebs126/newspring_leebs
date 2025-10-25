package com.springboot.ch09.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.springboot.ch09.dto.AddUserRequest;
import com.springboot.ch09.domain.User;
import com.springboot.ch09.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
	
	private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Long save(AddUserRequest dto) {
        return userRepository.save(User.builder()
                .email(dto.getEmail())
                .password(bCryptPasswordEncoder.encode(dto.getPassword()))
                .build()).getId();
    }
    
    public User findById(Long userId) {
    	return userRepository.findById(userId)
    			.orElseThrow(()-> new IllegalArgumentException("Unexpected user"));
    }
}
