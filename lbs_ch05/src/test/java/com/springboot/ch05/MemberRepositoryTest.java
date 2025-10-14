package com.springboot.ch05;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


@SpringBootTest
@AutoConfigureMockMvc  //MockMvc 구성 및 자동 생성
//@DataJpaTest
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
//        testService.deleteAll();
        testService.saveMember(new Member("hong", "1234", "홍길동", "hong@test.com", null));
	}
	
//	@Sql("/insert-members.sql")
	@Test
	void getAllMembers() {
		//when
		List<Member> members = testService.getAllMembers();
		
		//then
		assertThat(members.size()).isEqualTo(1);
	}
	
	@AfterEach
	public void cleanUp() {
		testService.deleteAll();
	}
}
