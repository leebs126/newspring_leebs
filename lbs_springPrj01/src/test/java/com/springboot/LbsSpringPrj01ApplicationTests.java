package com.springboot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LbsSpringPrj01ApplicationTests {
	@DisplayName("1 + 2는 3이다") //테스트이름
	@Test   //테스트 메서드
	void junitTest() {
		int a = 1;
		int b = 3;
		int sum = 3;
		Assertions.assertEquals(sum, a + b); //값이 같은지 확인
	}
	

	@DisplayName("1 * 3는 3이다") //테스트이름
	@Test   //테스트 메서드
	void junitTest2() {
		int a = 1;
		int b = 3;
		int result = 3;
		Assertions.assertEquals(result, a * b); //값이 같은지 확인
	}
}
