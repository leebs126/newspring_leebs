package com.springboot;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@Autowired
	TestService testService;
	
	@GetMapping("/test")
	public List<Member>  test() {
		List<Member> members = testService.getAllMembers();
		return members;
	}
	

	
//	public String test() {
//		return "Hell SpringBoot!";
//	}
	
}
