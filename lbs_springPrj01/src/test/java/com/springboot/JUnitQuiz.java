package com.springboot;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class JUnitQuiz {
	
	@Test
	public void junitTest() {
		String name1 = "홍길동";
		String name2 = "홍길동";
		String name3 = "홍길은";
		
		//모든 변수가 nu3ll인지 아닌지 확인
		assertThat(name1).isNotNull();
		assertThat(name2).isNotNull();
		assertThat(name3).isNotNull();
		
		//name1과 name2가 같은지 확인
		assertThat(name1).isEqualTo(name2);
		
		//name1과 name3이 다른지 확인
		assertThat(name1).isNotEqualTo(name3);
		
	}
	
	@Test
	public void junitTest2() {
		int number1 = 15;
		int number2 = 0;
		int number3 = -5;
		
		//number1이 양수인지 확인
		assertThat(number1).isPositive();
		
		//number2는 0인지 확인
		assertThat(number2).isZero();
		
		//number3은 음수인지 확인
		assertThat(number3).isNegative();
		
		//number1 이 number2보다 큰지 확인
		assertThat(number1).isGreaterThan(number2);
		
		//number3이 number2보다 작은지 비교
		assertThat(number3).isLessThan(number2);
		
		
		
	}

}
