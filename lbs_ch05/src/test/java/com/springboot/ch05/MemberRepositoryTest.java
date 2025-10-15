package com.springboot.ch05;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;


@SpringBootTest
@AutoConfigureMockMvc  //MockMvc 구성 및 자동 생성
//@DataJpaTest
@Transactional
public class MemberRepositoryTest {
	@Autowired
	protected MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext context;
	
	@Autowired
	private TestService testService;
	
	@BeforeEach  //태스트 실행 전 실행하는 메서드
 	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
										.build();
		 // 테스트용 데이터 초기화
        testService.deleteAll();
//        testService.saveMember(new Member("hong", "1234", "홍길동", "hong@test.com", null));
	}
	
//	@Sql("/insert-members.sql")
//	@Test
//	void getAllMembers() {
//		//when
//		List<Member> members = testService.getAllMembers();
//		
//		//then
//		assertThat(members.size()).isEqualTo(1);
//	}
//	

	
	
//	@Test
//	public void getMemberById() {
//		//when
//		Member member = testService.findMemberById("hong").get();
//		
//		//then
//		assertThat(member.getName()).isEqualTo("홍길동");
//	}
//	
//	@Test
//	public void getMemberByName() {
//		//when
//		Member member = testService.findMemberByName("홍길동").get();
//		
////		Member member = testService.findByName2("홍길동").get();
//		//then
//		assertThat(member.getMemId()).isEqualTo("hong");
//		
//	}
//	
//	@Test
//	@Rollback(false)
//	public void saveMember() {
//		//given
//		testService.saveMember(new Member("lee", "1234", "이길동", "lee@test.com", null));
//		Member member = testService.findMemberById("lee").get();
//		assertThat(member.getName()).isEqualTo("이길동");
//	}
	
	
	@Test
	@Rollback(false)
//	@Disabled("현재 테스트 제외 — 추후 복구 예정")
	public void saveMembers() {
	//given
	List<Member> members = List.of(new Member("hong", "1111", "홍길동", "hong@test.com", null)
									, new Member("lee", "1111", "이길동", "lee@test.com", null));
	//when
	testService.saveMembers(members);
	int size = testService.getAllMembers().size();
	
	//then
	assertThat(size).isEqualTo(2);
	
	}
	
	@Test
	public void deleteMemberById() {
		
		//given
		List<Member> members = List.of(new Member("hong", "1234", "홍길동", "hong@test.com", null)
									  , new Member("lee", "1234", "이길동", "lee@test.com", null));
		testService.saveMembers(members);

		//when
		testService.deleteMemberById("hong");
		
		//then
		int size = testService.getAllMembers().size();
		
		//then
		assertThat(size).isEqualTo(1);
		
	}
	
	@Test
	public void update() {
		// given
	    List<Member> members = List.of(
	        new Member("hong", "1234", "홍길동", "hong@test.com", null),
	        new Member("lee", "1234", "이길동", "lee@test.com", null)
	    );
	    testService.saveMembers(members);

	    // when
	    Member hong = testService.findMemberById("hong").get(); // hong 회원 조회
	    hong.setPwd("1111");                        // 비밀번호 수정
	    testService.updateMember(hong);             // 수정 반영

	    // then
	    Member updatedHong = testService.findMemberById("hong").get(); // 다시 조회
	    assertThat(updatedHong.getPwd()).isEqualTo("1234");        // 비밀번호가 변경되었는지 확인
	}
	
	@AfterEach
	public void cleanUp() {
		testService.deleteAll();
	}	
	
}
