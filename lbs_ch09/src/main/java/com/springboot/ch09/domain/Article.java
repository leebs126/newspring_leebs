package com.springboot.ch09.domain;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@EntityListeners(AuditingEntityListener.class)
@Entity
@Getter
@NoArgsConstructor(access=AccessLevel.PROTECTED)
public class Article {
//	@Id  //id필드를 기본키로 지정
//	@GeneratedValue(strategy = GenerationType.IDENTITY)  //기본키를 자동으로 1씩 증가
//	@Column(name="id", updatable = false)
//	private long id;
	
	@Id  // 기본키 지정
	@SequenceGenerator(
	    name = "ARTICLE_SEQ_GENERATOR",   // 시퀀스 제너레이터 이름
	    sequenceName = "ARTICLE_SEQ",     // 실제 오라클 시퀀스 이름
	    initialValue = 1,                 // 시작값
	    allocationSize = 1                // 증가 단위 (1씩 증가)
	)
	@GeneratedValue(
	    strategy = GenerationType.SEQUENCE,
	    generator = "ARTICLE_SEQ_GENERATOR"
	)
	@Column(name = "id", updatable = false)
	private Long id;
	
	@Column(name="title", nullable=false)  //'title'이라는 not null 컬럼과 매핑
	private String title;
	
	@Column(name="content", nullable=false)  
	private String content;
	
	@CreatedDate  //엔티티가 생성될 때 생성 시간 저장
	@Column(name="created_at")
	private LocalDateTime createdAt;
	
	@LastModifiedDate  //엔티티가 수정될 때 시간 저장
	@Column(name="updated_at")
	private LocalDateTime updatedAt;
	
	@Builder  //빌더패턴으로 객체 생성
	public Article(String title, String content) {
		this.title = title;
		this.content = content;
	}
	
	public void update(String title, String content) {
		this.title = title;
		this.content = content;
	}
}


