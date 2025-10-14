package com.springboot;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
@SpringBootTest
@AutoConfigureMockMvc  //MockMvc 구성 및 자동 생성
public class TestControllerTest {
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


	@DisplayName("getAllMembers: 모든 회원 정보 조회에 성공한다.")
	@Test
	public void GetAllMembers() throws Exception {
		//given
		final String url = "/test";
		 // when
        mockMvc.perform(get(url)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
		
		//then
                .andExpect(status().isOk())
                // Member 클래스의 필드명이 memId, name 이라고 가정
                .andExpect(jsonPath("$[0].memId").value("hong"))
                .andExpect(jsonPath("$[0].name").value("홍길동"))
                .andExpect(jsonPath("$[0].email").value("hong@test.com"));
	}
	
	
	
	
	@AfterEach
	public void cleanUp() {
		testService.deleteAll();
	}
}
