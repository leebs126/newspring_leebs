package com.springboot.ch05;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {
	
	Optional<Member> findByName(String name);

//	@Query("select m from Member m where m.name='홍길동'")
//	Optional<Member> findByName2(String name);
}
