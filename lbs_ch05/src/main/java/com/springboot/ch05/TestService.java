package com.springboot.ch05;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TestService {
	@Autowired
	MemberRepository memberRepository;
	
	public List<Member> getAllMembers(){
		return memberRepository.findAll();  //멤버 목록 얻기
	}

//	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void saveMember(Member member){
		memberRepository.save(member);  //멤버 저장
	}
	
	public void saveMembers(List<Member> members){
		memberRepository.saveAll(members);  //멤버 저장
	}
	
	public void deleteMemberById(String memId) {
		memberRepository.deleteById(memId);
	}
	
	public void deleteAll(){
		memberRepository.deleteAll();  //모든 멤버 삭제
	}
	
	public Optional<Member> findMemberById(String memId) {
		return memberRepository.findById(memId);  //맴버 정보 얻기
	}
	
	public Optional<Member> findMemberByName(String name) {
		return memberRepository.findByName(name);  //맴버 정보 얻기
	}
	
	public Optional<Member>  updateMember(Member member){
		 return Optional.of(memberRepository.save(member));
	}
 //	
//	public void saveMember(Member member){
//		memberRepository.insertMember(member);  //멤버 저장
//	}
//	public void deleteAll(){
//		memberRepository.deleteAllMember();  //모든 멤버 삭제
//	}
}
