package com.springboot;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member {
	private String memId;
	private String pwd;
	private String name;
	private String email;
	private Date joinDate;
}
