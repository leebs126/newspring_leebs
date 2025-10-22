package com.springboot.ch08.domain;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name="users")
@NoArgsConstructor(access=AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE) // 빌더/리플렉션용 생성자
@Getter
@Entity

public class User implements UserDetails{
	@Id  // 기본키 지정
	@SequenceGenerator(
	    name = "USER_SEQ_GENERATOR",   // 시퀀스 제너레이터 이름
	    sequenceName = "USER_SEQ",     // 실제 오라클 시퀀스 이름
	    initialValue = 1,                 // 시작값
	    allocationSize = 1                // 증가 단위 (1씩 증가)
	)
	@GeneratedValue(
	    strategy = GenerationType.SEQUENCE,
	    generator = "USER_SEQ_GENERATOR"
	)
	@Column(name="id", updatable=false)
	private long id;
	
	@Column(name="email", nullable=false, unique=true)
	private String email;
	
	@Column(name="password")
	private String password;
	
	@Builder
	public User(String email, String password, String auth) {
		this.email= email;
		this.password = password;
	}
	
	
	
	
	@Override   //권한 반환
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return  List.of(new SimpleGrantedAuthority("user"));
	}
	

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return email;
	}
	
	//계정 만료 여부 반환
	@Override
	public boolean isAccountNonExpired() {
		// 만료 되었는지 확인하는 로직
		return true;  // true --> 만료되지 않았음 
	}
	
	//계정 잠금 여부 반환
	@Override
	public boolean isAccountNonLocked() {
		// 계정 잠금되었는지 확인하는 로직
		return true;  //true --> 잠금되지 않았음
	}
	
	
	//패스워드의 만료 여부 반환
	@Override
	public boolean isCredentialsNonExpired() {
		// 패스워드가 만료되었는지 확인하는 로직
		return true;  //true --> 만료되지 않았음
	}
	
	//계정 사용 가능 여부 반환
	@Override
	public boolean isEnabled() {
		// 계정 사용 가능한지 확인하는 로직
		return true;  //true --> 사용 가능
	}
	
	
}
