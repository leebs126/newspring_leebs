package com.springboot.ch05;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
	@Autowired
	MemberRepository memberRepository;
	
	public void test() {
		memberRepository.save(new Member("hong", "1234", "홍길동", "hong@test.com", null));
		
	}
}
