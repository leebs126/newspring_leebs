package com.springboot.ch05;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {
	@Autowired
	MemberRepository memberRepository;
	
	public List<Member> getAllMembers(){
		return memberRepository.findAll();  //멤버 목록 얻기
	}
	
	public void saveMember(Member member){
		memberRepository.save(member);  //멤버 저장
	}
	
	public void deleteAll(){
	memberRepository.deleteAll();  //모든 멤버 삭제
}
	
//	public Member findMemberById(String id) {
//		return memberRepository.findById();  //맴버 정보 얻기
//	}
//	
//	public void saveMember(Member member){
//		memberRepository.insertMember(member);  //멤버 저장
//	}
//	public void deleteAll(){
//		memberRepository.deleteAllMember();  //모든 멤버 삭제
//	}
}
