package com.springboot;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;



@Mapper
public interface MemberRepository {
	 public List<Member> selectAllMembers() throws DataAccessException;
	 public Member selectMemberById(String memId) throws DataAccessException;
	 public void insertMember(Member member) throws DataAccessException ;
	 public void deleteAllMember() throws DataAccessException;
}
